from stbt import match, match_text, match_all, press, wait_until, wait_for_motion, press_until_match, ocr, draw_text, Region, is_screen_black
import sys
import time
import stbt
import requests
import os
from time import sleep
from pagebase import key_press, getText, MatchParameters, Read_Data_CSV, wait_for_motion_detect, passMessageWith_Screenshots, failMessageWith_Screenshots, infoMessage, infoMessageWith_Screenshots, passMessage, failMessage, infoMessage, wait_for_element


def test_Amazon_HandsOn():
   
      func_name = sys._getframe().f_code.co_name
      test = 'tests/' + os.path.basename(__file__) + '::' + func_name
      
      print (test)
      """ Navigating to Roku Home Screen """
      
      to_roku_home()
      press("KEY_OK")     
      sleep(2)
      
      print ("Starting")
      """ Opening Amazon Prime Application and Validating Amazon Home Page """
      imagePath = "images/Amazon_app/Amazon_app_icon.png"
      press_until_match ("KEY_DOWN", imagePath)
      navigate_to_tile (imagePath)
      press("KEY_OK")    
      sleep(2) 
#       infoMessageWith_Screenshots ("Clicked on Amazon App Icon")
      sleep(5)  
           
      imagePath = "images/Amazon_app/TV_category_button.png"
      press_until_match ("KEY_DOWN", imagePath)
      sleep(2)  
      press("KEY_OK")   
#       infoMessageWith_Screenshots ("Clicked on Amazon TV Category Button")
      sleep(5)  
      
      imagePath = "images/Amazon_app/Featured_show_icon.png"
      press("KEY_RIGHT")
      press_until_match ("KEY_DOWN", imagePath)
      sleep(2)
      imagePath = "images/Amazon_app/Original_series_icon.png"
      press_until_match ("KEY_DOWN", imagePath)
      sleep(2)  
      press("KEY_OK")     
#       infoMessageWith_Screenshots ("Clicked on Original Show Category Button")
      sleep(5)
      
      imagePath = "images/Amazon_app/Show_icon.png"
      press_until_match ("KEY_RIGHT", imagePath)
      sleep(2)  
      metadata = getText (50, 442, 1254, 605)
      print (metadata)
      press("KEY_OK")   
#       infoMessageWith_Screenshots ("Clicked on Show Icon")
      sleep(5)  
    
      imagePath = "images/Amazon_app/Episode_one_icon.png"
      press_until_match ("KEY_RIGHT", imagePath)
      sleep(2)  
      metadata = getText (50, 442, 1254, 605)
      print (metadata)
      press("KEY_OK")   
#       infoMessageWith_Screenshots ("Clicked on Show Icon")
      sleep(5)
       
      
      
#       wait_for_element(imagePath, "Home Icon in Amazon Home Page", timeout_secs=20)
      sleep(5)
#       to_roku_home()


def open_Show(test, image):
    for _ in range(3): 
            result = match(image , region=Region.ALL)
            episode = getText(result.region.x, 135, result.region.right, result.region.bottom)   
            if Read_Data_CSV(test, 'Episode') in episode:
                        key_press("KEY_OK", "Episode")
                        break
            else:   
                press("KEY_RIGHT") 
                sleep(3)   
            
    else:
        assert False, failMessageWith_Screenshots("The particular Episode is not displaying in the screen")


def add_verification_NBC():
    
    start = getText(176, 528, 243, 553)
    
    sleep(3)
    end = getText(176, 528, 243, 553)
    
    if start.replace('O', '0').split(':', len(start))[1] in end.replace('O', '0').split(':', len(end))[1]:
        infoMessageWith_Screenshots ("Advertisement is playing")
        
        wait_until(lambda: getText(176, 528, 243, 553).replace('O', '0').split(':', len(end))[1] not in end.replace('O', '0').split(':', len(end))[1],
                      timeout_secs=60)
    else:
        infoMessageWith_Screenshots ("Advertisement is not playing")  


def to_roku_home():
    for _ in range(5):
        press("KEY_HOME")
        
        if wait_until(lambda: match("selftest-screenshots/screenshot.png"),
                      timeout_secs=10):
            passMessageWith_Screenshots("Roku Home Screen is displayed")
            sleep(3)
            break
    else:
       assert False, "Fail/ Failed to find Roku Home after pressing HOME 5 times"


def find_selection(frame=None):
    import pagebase

    return pagebase.find_selection_horizontal_repeat(
        frame, "selftest-screenshots/roku-menu-selection-background.png")


def navigate_to_tile(image):
    from time import sleep

    for _ in range(15):
        key = _direction(image)
        if key:
            press(key)
            sleep(1)
        else:
            break


def _direction(target_image, frame=None):
    """Return the key we should press to move the selection one step closer to
    the target tile.

    >>> from utils import load_image
    >>> _direction("images/roku-bbc-iplayer-tile.png", frame=load_image("selftest-screenshots/roku-angry-birds-tile-selected.png"))
    'KEY_DOWN'
    >>> _direction("images/roku-bbc-iplayer-tile.png", frame=load_image("selftest-screenshots/roku-bbc-sport-tile-selected.png"))
    'KEY_RIGHT'
    >>> _direction("images/roku-bbc-iplayer-tile.png", frame=load_image("selftest-screenshots/roku-amazon-video-tile-selected.png"))
    'KEY_LEFT'
    >>> _direction("images/roku-bbc-iplayer-tile.png", frame=load_image("selftest-screenshots/roku-bbc-iplayer-tile-selected.png"))
    False
    """

    from stbt import get_frame

    if frame is None:
        frame = get_frame()

    selection = find_selected_tile(frame)
    assert selection, "Fail/ Didn't find currently selected tile"

    target = match(target_image, frame)
    assert target, "The target tile isn't visible"

    if selection.region.contains(target.region):
        # Already selected
        return False

    if target.region.y > selection.region.bottom:
        return "KEY_DOWN"
    if target.region.x > selection.region.right:
        return "KEY_RIGHT"
    if target.region.right < selection.region.x:
        return "KEY_LEFT"

    assert False, "Couldn't figure out how to get from %s to %s" % (
        selection.region, target.region)


def find_selected_tile(frame=None):
    """Find the selected tile in the grid of players on the Roku Home screen.

    >>> from utils import load_image
    >>> print find_selected_tile(frame=load_image("selftest-screenshots/roku-bbc-iplayer-tile-selected.png"))
    MatchResult(..., match=True, region=Region(x=328, y=151, width=214, height=166), ...)
    >>> print find_selected_tile(frame=load_image("selftest-screenshots/roku-home.png")).match
    False
    """

    import cv2
    from stbt import get_frame
    from pagebase import load_image

    if frame is None:
        frame = get_frame()

    frame = cv2.bitwise_and(frame,
                            load_image("selftest-screenshots/roku-tile-selection-mask.png"))
    return match("selftest-screenshots/roku-tile-selection.png", frame)


def text_read():    
    from time import sleep
    
    if  match("images/Nbc_Sports_app/nbc_sports_WY.png"):
        press("KEY_OK")
        sleep(3)
        
    if  match("images/Nbc_Sports_app/nbc_sports_YV.png"):
        press("KEY_OK")
        sleep(3)
        
    '''Authentication CODE Read'''
    code1 = getText(470, 365, 800, 420)
    code1 = code1.replace(" ", "")
    code = list(code1)
    print code
    print code[0]
    print code[1]
    print code[2]
    print code[3]
    print code[4]
    print code[5]
    print code[6]
    '''Set S for equivalent image'''
    if  match("images/Nbc_Sports_app/nbc_sports_S.png"):
            result = match("images/Nbc_Sports_app/nbc_sports_S.png")
            x = result.region.x
            print result.region.x
            print result.region.y
            if(x >= 480 and x <= 520):
                code[0] = 'S'
            elif(x >= 521 and x <= 570):
                code[1] = 'S'
            elif(x >= 571 and x <= 605):
                code[2] = 'S'
            elif(x >= 606 and x <= 640):
                code[3] = 'S'
            elif(x >= 641 and x <= 690):
                code[4] = 'S'
            elif(x >= 691 and x <= 734):
                code[5] = 'S'
            elif(x >= 735 and x <= 802):
                code[6] = 'S'
    '''Set Z for equivalent image'''
    if match("images/Nbc_Sports_app/nbc_sports_Z.png"):
            result1 = match("images/Nbc_Sports_app/nbc_sports_Z.png")
            x = result1.region.x
            print result1.region.x
            print result1.region.y
            if(x >= 480 and x <= 520):
                code[0] = 'Z'
            elif(x >= 521 and x <= 570):
                code[1] = 'Z'
            elif(x >= 571 and x <= 605):
                code[2] = 'Z'
            elif(x >= 606 and x <= 640):
                code[3] = 'Z'
            elif(x >= 641 and x <= 690):
                code[4] = 'Z'
            elif(x >= 691 and x <= 734):
                code[5] = 'Z'
            elif(x >= 735 and x <= 802):
                code[6] = 'Z'
    '''Set 2 for equivalent image'''
    if  match("images/Nbc_Sports_app/nbc_sports_2.png"):
            result3 = match("images/Nbc_Sports_app/nbc_sports_2.png")
            x = result3.region.x
            print result3.region.x
            print result3.region.y
            if(x >= 480 and x <= 520):
                code[0] = '2'
            elif(x >= 521 and x <= 570):
                code[1] = '2'
            elif(x >= 571 and x <= 605):
                code[2] = '2'
            elif(x >= 606 and x <= 640):
                code[3] = '2'
            elif(x >= 641 and x <= 690):
                code[4] = '2'
            elif(x >= 691 and x <= 734):
                code[5] = '2'
            elif(x >= 735 and x <= 802):
                code[6] = '2'
    '''Set B for equivalent image'''
    if  match("images/Nbc_Sports_app/nbc_sports_B.png"):
            result4 = match("images/Nbc_Sports_app/nbc_sports_B.png")
            x = result4.region.x
            print result4.region.x
            print result4.region.y 
            if(x >= 480 and x <= 520):
                code[0] = 'B'
            elif(x >= 521 and x <= 570):
                code[1] = 'B'
            elif(x >= 571 and x <= 605):
                code[2] = 'B'
            elif(x >= 606 and x <= 640):
                code[3] = 'B'
            elif(x >= 641 and x <= 690):
                code[4] = 'B'
            elif(x >= 691 and x <= 734):
                code[5] = 'B'
            elif(x >= 735 and x <= 802):
                code[6] = 'B'
    '''Set 8 for equivalent image'''            
    if  match("images/Nbc_Sports_app/nbc_sports_8.png"):
            result5 = match("images/Nbc_Sports_app/nbc_sports_8.png")
            x = result5.region.x
            print result5.region.x
            print result5.region.y
            if(x >= 480 and x <= 520):
                code[0] = '8'
            elif(x >= 521 and x <= 570):
                code[1] = '8'
            elif(x >= 571 and x <= 605):
                code[2] = '8'
            elif(x >= 606 and x <= 640):
                code[3] = '8'
            elif(x >= 641 and x <= 690):
                code[4] = '8'
            elif(x >= 691 and x <= 734):
                code[5] = '8'
            elif(x >= 735 and x <= 802):
                code[6] = '8'
    '''Set 5 for equivalent image'''
    if  match("images/Nbc_Sports_app/nbc_sports_5.png"):
            result5 = match("images/Nbc_Sports_app/nbc_sports_5.png")
            x = result5.region.x
            print result5.region.x
            print result5.region.y
            if(x >= 480 and x <= 520):
                code[0] = '5'
            elif(x >= 521 and x <= 570):
                code[1] = '5'
            elif(x >= 571 and x <= 605):
                code[2] = '5'
            elif(x >= 606 and x <= 640):
                code[3] = '5'
            elif(x >= 641 and x <= 690):
                code[4] = '5'
            elif(x >= 691 and x <= 734):
                code[5] = '5'
            elif(x >= 735 and x <= 802):
                code[6] = '5'
    '''Concatenate final code'''            
    code1 = code[0] + code[1] + code[2] + code[3] + code[4] + code[5] + code[6]
    print code
    print code1
    return code1
