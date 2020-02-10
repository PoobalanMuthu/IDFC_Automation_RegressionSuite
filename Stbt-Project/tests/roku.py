from stbt import match, match_text,match_all, press, wait_until, wait_for_motion, press_until_match, ocr, draw_text, Region,is_screen_black
import sys
import time
import stbt
import requests
import os
from time import sleep
from pagebase import key_press,getText,MatchParameters,Read_Data_CSV, wait_for_motion_detect, passMessageWith_Screenshots, failMessageWith_Screenshots, infoMessageWith_Screenshots, passMessage, failMessage, infoMessage, wait_for_element
from gettext import gettext

def test_mtv_tv():
    from time import sleep
    try:
       func_name = sys._getframe().f_code.co_name
       test='tests/'+os.path.basename(__file__)+'::'+func_name
       sleep(20)
       """Navigating to Roku Home Screen"""      
       to_roku_home()
       press("KEY_OK")     
       sleep(2)
    except Exception,e:
        assert False, failMessage("Error occured : %s" %e.args)






def test_spectrum_tv():
    from time import sleep
    try:
       func_name = sys._getframe().f_code.co_name
       test='tests/'+os.path.basename(__file__)+'::'+func_name
       sleep(20)
       """Navigating to Roku Home Screen"""      
       to_roku_home()
       press("KEY_OK")     
       sleep(2)
      
       """Opening Spectrum TV App"""      
       press_until_match("KEY_UP", "images/Spectrum_Tv/specrtumTV_icon.png")
       navigate_to_tile("images/Spectrum_Tv/specrtumTV_icon.png")
       press("KEY_OK")     
       passMessageWith_Screenshots ("Clicked on Spectrum TV App")
       
       if stbt.is_screen_black():
           infoMessageWith_Screenshots("Black Screen is detected")
       sleep(3)
       
       sleep(15)
       
       #wait_for_element("images/Spectrum_Tv/spectrum_home_screen.png","Spectrum TV App Home Page is displayed",timeout_secs=20)
       if match("images/Spectrum_Tv/parental_unblock.png"):
              infoMessageWith_Screenshots("Parental Control is enabled")
              press("KEY_BACK")
              sleep(2)
              
       else :
           wait_for_element("images/Spectrum_Tv/spectrum_home_screen.png","Spectrum TV App Home Page",timeout_secs=20)
       press("KEY_DOWN")
       sleep(2)
       press("KEY_OK")
       sleep(8)
       name=getText(200,500,850,557)
       print name
       texts=getText(200,515,850,688)
       print texts
       cc3=texts.replace('\n', ' ').replace('\r','')
       print cc3
       passMessageWith_Screenshots("Show name----->"+name)
       passMessageWith_Screenshots("Show Details----->"+cc3)
       show=getText(50,536,720,580)
       print show
       
       key_press("KEY_OK","Play button")
       sleep(5)
       if wait_until(lambda: not match("images/Spectrum_Tv/spectrum_app_guide.png",match_parameters=stbt.MatchParameters(match_method="ccoeff-normed", confirm_method="none")),timeout_secs=20):
           if match("images/Spectrum_Tv/parental_unblock.png",match_parameters=stbt.MatchParameters(match_method="ccoeff-normed", confirm_method="none")):
                    passMessageWith_Screenshots("Parental Control Setting is enabled")
                    press("KEY_OK")
                    sleep(5)
                    for _ in range(4):
                        press("KEY_UP")
                        sleep(3)
                        press("KEY_RIGHT")
                        sleep(3)
                    press("KEY_OK")
                    sleep(3)
                
       sleep(2)
       if match("images/Spectrum_Tv/spectrum_view_details.png"):
                    show_name=getText(50,530,720,575)
       else:
           press("KEY_DOWN")
           sleep(5)
           #wait_for_motion_detect(mask=None,timeout_secs=20)
           show_name=getText(50,530,720,575) 
       print show_name      
       if show_name.split(' ')[0] == name.split(' ')[0] :
           passMessageWith_Screenshots("The Selected show "+name+" is currently playing")
           
       else :
           failMessageWith_Screenshots("The Selected show "+name+" show is not playing")
           
       '''AD Verification'''   
       sleep(2)
       flag =0
       if wait_until(lambda: not match("images/Spectrum_Tv/spectrum_view_details.png",match_parameters=stbt.MatchParameters(match_method="ccoeff-normed", confirm_method="none")),timeout_secs=30):   
           sleep(3)
           if match("images/Spectrum_Tv/spectrum_cnn_notlive_logo.png"):
               sleep(5)
               caption=getText(250,71,915,131)
               passMessageWith_Screenshots("Ad is not playing")
           else :
               passMessageWith_Screenshots("Ad is Playing")
               flag=1
           
           
       '''Glittering of Video Validation'''
       for m in stbt.detect_motion(noise_threshold=1.0, timeout_secs=20):
            if not m.motion and not stbt.is_screen_black():
                infoMessage("Jerkiness in the video is captured at %s" % m.time)
                break
               
            
       if wait_until(lambda: not match("images/Spectrum_Tv/spectrum_view_details.png",match_parameters=stbt.MatchParameters(match_method="ccoeff-normed", confirm_method="none")),timeout_secs=30):
             sleep(15)
             if flag == 1:
                for _ in range(100):
                     cc = getText(250,71,915,131)
                     cc3=cc.replace('\n', ' ').replace('\r','')
                     if (len(cc.strip()) >= 10) : 
                         passMessageWith_Screenshots ("Closed Caption is displayed in the video as===> "+cc3)
                         break 
             wait_for_motion_detect(mask=None,timeout_secs=20)
       '''To Validate on demand video Functionality'''
       press("KEY_BACK")
       sleep(3)
       press_until_match("KEY_DOWN", "images/Spectrum_Tv/settings_Icon.png")
       key_press("KEY_OK","Settings Icon")
       sleep(2)
       press("KEY_DOWN")
       sleep(3)
       press("KEY_RIGHT")
       sleep(3)
       if match ("images/Spectrum_Tv/spectrum_parental_on.png"):
           press("KEY_DOWN")
           sleep(3)
           press("KEY_OK")
           sleep(3)
           infoMessageWith_Screenshots("TV shows with rating TV-MA and Movies with rating PG are selected for Parental Control Validations.")
           if match ("images/Spectrum_Tv/spectrum_app_TV_MA.png"):
               for _ in range(5):
                   press("KEY_RIGHT")
                   sleep(3)
               press("KEY_OK")
               sleep(3)
               for _ in range(4):
                        press("KEY_UP")
                        sleep(3)
                        press("KEY_RIGHT")
                        sleep(3)
               press("KEY_OK")
               sleep(3)
               infoMessageWith_Screenshots("TV-MA video contents is Blocked")
           press("KEY_BACK")
           sleep(3)
           press("KEY_BACK")
           sleep(3)
           
           
       wait_for_element("images/Spectrum_Tv/settings_Icon.png","Spectrum TV App Home Page is displayed",timeout_secs=20)
       press("KEY_UP")
       sleep(2)
       press("KEY_UP")
       sleep(2)
       press("KEY_OK")
       sleep(2)
       press_until_match("KEY_DOWN", "images/Spectrum_Tv/spectrum_app_kids.png")
       sleep(2)
       movie_name=getText(770,440,1160,490)
       movie_type=getText(782,492,925,514)
       print movie_type
       infoMessageWith_Screenshots("Selecting a movie with parental control for type PG")
       
       infoMessageWith_Screenshots("Selected Movie Name "+movie_name)
       infoMessageWith_Screenshots("Selected Movie Rating "+movie_type)
              
       press("KEY_OK")
       if stbt.is_screen_black():
           infoMessageWith_Screenshots("Black Screen is detected")
       sleep(3)
       movie_details=getText(288,250,1200,357)
       infoMessageWith_Screenshots("Selected Movie details "+movie_details)
       if match("images/Spectrum_Tv/spectrum_resume.png"):
           key_press("KEY_OK","Resume button")
           sleep(3)
           if match("images/Spectrum_Tv/spectrum_pin.png"):
               for _ in range(4):
                        press("KEY_UP")
                        sleep(3)
                        press("KEY_RIGHT")
                        sleep(3)
               press("KEY_OK")
               if stbt.is_screen_black():
                    infoMessageWith_Screenshots("Black Screen is detected")
                    sleep(3)
               infoMessageWith_Screenshots("Parental Control Pin is entered and movie is unblocked")
       else :
            press("KEY_OK")
            if stbt.is_screen_black():
               infoMessageWith_Screenshots("Black Screen is detected")
            sleep(3)
            infoMessageWith_Screenshots("Movie is Playing for blocked Rating")
       sleep(10)
       wait_for_motion_detect(mask=None,timeout_secs=20)

       '''To Validate on demand video Functionality'''
       #wait_for_element("images/Spectrum_Tv/spectrum_app_guide.png","Spectrum TV App Home Page is displayed",timeout_secs=20)
       press("KEY_BACK")
       sleep(3)
       press("KEY_BACK")
       sleep(3)
       press_until_match("KEY_DOWN", "images/Spectrum_Tv/spectrum_app_adult.png")
       sleep(2)
       press("KEY_LEFT")
       sleep(2)
       movie_name=getText(770,440,1160,490)
       movie_type=getText(782,492,925,514)
       print movie_type
       infoMessageWith_Screenshots("Selecting a movie with parental control  for type TV-MA")
       
       infoMessageWith_Screenshots("Selected Movie Name "+movie_name)
       infoMessageWith_Screenshots("Selected Movie Rating "+movie_type)
              
       press("KEY_OK")
       if stbt.is_screen_black():
           infoMessageWith_Screenshots("Black Screen is detected")
       sleep(3)
       movie_details=getText(288,250,1200,450)
       infoMessageWith_Screenshots("Parental Control information: "+movie_details)
       
       if match("images/Spectrum_Tv/spectrum_resume.png"):
           press("KEY_OK")
           infoMessageWith_Screenshots("Resume Button is clicked")
           sleep(3)
           if match("images/Spectrum_Tv/spectrum_pin.png"):
               for _ in range(4):
                        press("KEY_UP")
                        sleep(3)
                        press("KEY_RIGHT")
                        sleep(3)
               press("KEY_OK")
               if stbt.is_screen_black():
                    infoMessageWith_Screenshots("Black Screen is detected")
                    sleep(3)
               infoMessageWith_Screenshots("Parental Control Pin is entered and movie is unblocked")
       else :
            press("KEY_OK")
            if stbt.is_screen_black():
               infoMessageWith_Screenshots("Black Screen is detected")
            sleep(3)
            infoMessageWith_Screenshots("Movie is Playing")
       sleep(10)
       wait_for_motion_detect(mask=None,timeout_secs=20)
      
       """Motion Detection after Pressing the Pause Button """      
       key_press("KEY_PLAYPAUSE","'Pause'")
       sleep(2)
       try:
           wait_for_motion(mask=None,timeout_secs=5)
           failMessageWith_Screenshots("Motion detected - after clicking the Pause Button")
       except Exception,e:  
            passMessageWith_Screenshots("Motion Not detected - after clicking the Pause Button")
       key_press("KEY_PLAYPAUSE","'Play'")
       sleep(20)    
       for _ in range(200):
                     cc = getText(323,456,880,650)
                     cc3=cc.replace('\n', ' ').replace('\r','')
                     if (len(cc.strip()) >= 10) : 
                         passMessageWith_Screenshots ("Closed Caption is displayed in the video as===> "+cc3)
                         break 
       """Verify Video is Rewinded or not"""
       key_press("KEY_PLAYPAUSE","Pause Button")
       sleep(2)
       startTime_before=getText(120,610,190,650)
       print startTime_before
       press("KEY_REWIND")
       sleep(7)
       key_press("KEY_PLAYPAUSE","Play Button")
       sleep(2)
       key_press("KEY_PLAYPAUSE","Play Button")
       sleep(2)
       startTime_after =getText(120,610,190,650)
       print startTime_after
       startTime_after=startTime_after.replace(")","")
       startTime_after=startTime_after.replace("L","1")
       startTime_after=startTime_after.replace("O","0")
       if startTime_after < startTime_before:
                 passMessageWith_Screenshots("Video is rewound from "+startTime_before+" to "+startTime_after)
       else:
                 passMessageWith_Screenshots("Video is rewound ")
       press_until_match("KEY_BACK", "images/Spectrum_Tv/spectrum_resume.png")
       press("KEY_RIGHT")
       sleep(3)
       press("KEY_OK")
       sleep(3)
       infoMessageWith_Screenshots("Movie is added to the watchlist")
       if match("images/Spectrum_Tv/spectrum_remove_watchlist.png"):
               press("KEY_BACK")
               sleep(2)
               press("KEY_BACK")
               sleep(2)
               press("KEY_UP")
               sleep(2)
               press("KEY_OK")
               sleep(5)
               press("KEY_DOWN")
               sleep(2)
               watchlist_movie_name=getText(770,440,1160,490)
               for _ in range(10):
                        if movie_name == watchlist_movie_name :
                            passMessageWith_Screenshots (watchlist_movie_name+" Movie is added to the watchlist --- verified")
                            break
                        else:
                            press("KEY_RIGHT")
                            sleep(3)
                            watchlist_movie_name=getText(770,440,1160,490)
                            #press("KEY_RIGHT")
                            
               press("KEY_BACK")
               sleep(2)
               
       '''To Validate Favorite Functionality'''
       press_until_match("KEY_DOWN", "images/Spectrum_Tv/settings_Icon.png")
       key_press("KEY_OK","Settings Icon")
       sleep(2)
       press("KEY_RIGHT")
       sleep(2)
       press("KEY_DOWN")
       sleep(2)
       press("KEY_DOWN")
       sleep(2)
       key_press("KEY_OK","Manage Favorite Tab")
       sleep(2)
       press("KEY_OK")
       sleep(2)
       key_press("KEY_OK","Clear All")
       sleep(2)
       press("KEY_RIGHT")
       sleep(2)
       press("KEY_RIGHT")
       sleep(2)
       key_press("KEY_OK","ABC network")
       if wait_until(lambda: not match("images/Spectrum_Tv/favorite_icon.png",match_parameters=stbt.MatchParameters(match_method="ccoeff-normed", confirm_method="none")),timeout_secs=10):
          infoMessageWith_Screenshots("Network added as Favorite")  
       press("KEY_BACK")
       sleep(3)  
       press_until_match("KEY_BACK", "images/Spectrum_Tv/exit_app_icon.png") 
       press("KEY_LEFT")
       sleep(2)
       key_press("KEY_OK","Exit App button")
       sleep(2)
       press("KEY_OK")     
       passMessageWith_Screenshots ("Clicked on Spectrum TV App")
       
       wait_for_element("images/Spectrum_Tv/spectrum_home_screen.png","Spectrum TV App Home Page is displayed",timeout_secs=30)
       press("KEY_DOWN")
       sleep(2)
       key_press("KEY_OK","Guide icon")  
       sleep(2)
       press("KEY_LEFT")
       sleep(2)
       press("KEY_LEFT")
       sleep(2)
       press("KEY_DOWN")
       sleep(2)
       press("KEY_DOWN")
       sleep(2)
       press_until_match("KEY_UP", "images/Spectrum_Tv/spectrum_app_favourites.png")
       key_press("KEY_OK","Filter by Favorite channel icon")  
       wait_for_element("images/Spectrum_Tv/abc_icon.png","Spectrum TV App Home Page is displayed",timeout_secs=20)
       passMessageWith_Screenshots("Favorite Functionality is Verified")
      
       to_roku_home()
    except Exception,e:
        assert False, failMessage("Error occured : %s" %e.args)




'''Test to Validate NBC Sports App in ROKU'''
def nbc_sports_show_count():
    
    '''all_buttons =list(match_all("images/Nbc_Sports_app/nbc_sports_filter_clip.png"))
    print len(all_buttons)'''
    
    from time import sleep
    try:
       func_name = sys._getframe().f_code.co_name
       test='tests/'+os.path.basename(__file__)+'::'+func_name
       cout=0
       count = getText(1185,149,1220,169)
       print count
       text = getText(62,313,246,349)
       for _ in range(10):
                all_buttons = list(stbt.match_all("images/Nbc_Sports_app/nbc_sports_filter_clip.png"))
                print text
                press("KEY_DOWN")
                sleep(3)
                press("KEY_DOWN")
                sleep(3)
                ep = getText(62,313,246,349)
                print ep
                print cout
                if text == ep:
                    break;
                print len(all_buttons)
                cout=cout+len(all_buttons)
                print cout
       print "hello"
       count=count.replace("I","")
       print count
       total=str(cout)
       print total
       if count==total :
           passMessageWith_Screenshots("Total Numbers of videos"+count+"is verified")
       else:
           failMessageWith_Screenshots("Video count is not verified")
                  
       
    except Exception,e:
        assert False, failMessage("Error occured : %s" %e.args)
 
    
def test_nbc_sports():
    from time import sleep
    try:
       func_name = sys._getframe().f_code.co_name
       test='tests/'+os.path.basename(__file__)+'::'+func_name
          
       """Navigating to Roku Home Screen"""      
       to_roku_home()
       press("KEY_OK")     
       sleep(2)
      
       """Opening NBC SPORTS App"""      
       press_until_match("KEY_UP", "images/Nbc_Sports_app/nbc_sports_app.png")
       if match("images/Nbc_Sports_app/nbc_sports_selectedapp.png"):
                    sleep(2)
                    press("KEY_OK")
                    passMessageWith_Screenshots ("Clicked on NBC SPORTS App")
       else:         
         navigate_to_tile("images/Nbc_Sports_app/nbc_sports_app.png")
         press("KEY_OK")     
         passMessageWith_Screenshots ("Clicked on NBC SPORTS App")
       sleep(10)
       
       wait_for_element("images/Nbc_Sports_app/nbc_sports_home.png","NBC SPORTS Home Page is displayed",timeout_secs=20)
              
       """Validate for scroll up & down in home screen"""
       count=0
       show=""
       if match("images/Nbc_Sports_app/nbc_sports_home.png"):
            episode=""
            for _ in range(10):
                str2 = getText(60,532,745,660)
                print episode
                print str2
                #str2=str2.replace(" ","")
                #print str2
                if (str2 == episode and str2!=""):
                     infoMessageWith_Screenshots ("Selected video is  -->"+str2)
                     #assert True, passMessageWith_Screenshots("Scroll Down till the end of screen is done")
                     passMessageWith_Screenshots("Scroll Down Verfication Passed")
                     break
                else:
                   episode = str2
                   press("KEY_DOWN")
                   sleep(3)
                   count = count+1
            else:
                 assert False, failMessageWith_Screenshots("Scroll Down verification failed")
                             
            print count
            for _ in range(count):
                print  count
                press("KEY_UP")
                sleep(3)
                if match("images/Nbc_Sports_app/nbc_sports_scrollup.png"):
                    passMessageWith_Screenshots("Scroll Up Verfication Passed")
                    press("KEY_DOWN")
                    break
            else:
                 assert False, failMessageWith_Screenshots("Scroll Up verification failed")
        
       """Validate menu icons & screens - Featured"""       
       if match("images/Nbc_Sports_app/nbc_sports_home.png"):
                    sleep(2)
                    if match("images/Nbc_Sports_app/nbc_sports_home.png"):
                        infoMessageWith_Screenshots ("Feature icon and screen is displayed")
                    else:         
                        infoMessageWith_Screenshots ("Feature icon and screen is not displayed")
       else:         
         infoMessageWith_Screenshots ("Feature icon is not displyed")
        
       """Validate menu icons & screens - Live&Upcoming"""    
       press("KEY_RIGHT")
       sleep(3) 
       if match("images/Nbc_Sports_app/nbc_sports_live.png"):
                    sleep(2)
                    infoMessageWith_Screenshots ("Live&Upcoming icon is displyed")
                    if match("images/Nbc_Sports_app/nbc_sports_live.png"):
                        infoMessageWith_Screenshots ("Live&Upcoming screen is displayed")
                    else:         
                        infoMessageWith_Screenshots ("Live&Upcoming screen is not displayed")
       else:         
         infoMessageWith_Screenshots ("Live&Upcoming icon is not displayed")
       
       """Validate menu icons & screens - Replays"""       
       press("KEY_RIGHT")
       sleep(3)  
       if match("images/Nbc_Sports_app/nbc_sports_replays.png"):
                    sleep(2)
                    infoMessageWith_Screenshots ("Replays icon is displayed")
                    if match("images/Nbc_Sports_app/nbc_sports_replayscreen.png"):
                         infoMessageWith_Screenshots ("Replays screen is displayed")
                    else:         
                        infoMessageWith_Screenshots ("Replays screen is not displayed")
       else:         
         infoMessageWith_Screenshots ("Replays icon is not displayed")
       
       """Validate menu icons & screens - Highlights"""       
       press("KEY_RIGHT")  
       sleep(3)
       if match("images/Nbc_Sports_app/nbc_sports_highlights.png"):
                    sleep(2)
                    infoMessageWith_Screenshots ("Highlights icon is displayed")
                    if match("images/Nbc_Sports_app/nbc_sports_highlightscreen.png"):
                         infoMessageWith_Screenshots ("Highlights screen is displayed")
                    else:         
                        infoMessageWith_Screenshots ("Highlights screen is not displayed")
       else:         
         infoMessageWith_Screenshots ("Highlights icon is not displayed")
       
       """Validate menu icons & screens - Settings"""       
       press("KEY_RIGHT")
       sleep(3)  
       if match("images/Nbc_Sports_app/nbc_sports_settings.png"):
                    sleep(2)
                    infoMessageWith_Screenshots ("Settings icon is displayed")
                    if match("images/Nbc_Sports_app/nbc_sports_settingscreen.png"):
                         infoMessageWith_Screenshots ("Settings screen is displayed")
                    else:         
                        infoMessageWith_Screenshots ("Settings screen is not displayed")
       else:         
         infoMessageWith_Screenshots ("Settings icon is not displayed")
         
       """Navigate to Featured Section"""
       for _ in range(6):
           press("KEY_LEFT")
           sleep(3)
           if match("images/Nbc_Sports_app/nbc_sports_home.png"):
               break
            
       """Navigate to video"""
       for _ in range(2):
           press("KEY_DOWN")
           sleep(3)                
        
       """Playing a video in NBC SPORTS App from Featured Home screen and validate for pre-ads and successful play """       
       press("KEY_OK")
       sleep(2)
       if match("images/Nbc_Sports_app/nbc_sports_resume.png"):
                    sleep(2)
                    press("KEY_OK")
                    passMessageWith_Screenshots ("Resume button is clicked")
                    
                    if match("images/Nbc_Sports_app/nbc_sports_buffering.png"):
                         passMessageWith_Screenshots ("Featured Video is currently buffering")
       else:
        if match("images/Nbc_Sports_app/nbc_sports_buffering.png"):
            passMessageWith_Screenshots ("Featured Video is currently buffering") 
       sleep(8)
       
       """Verify Video motion"""
       wait_for_motion_detect(mask=None,timeout_secs=15)
       
       for _ in range(2):
        press("KEY_BACK")
        sleep(3)  
               
       """Playing a video in NBC SPORTS App from lIVE&UPCOMING screen and validate successful authentication"""
       press("KEY_RIGHT")
       sleep(3)
       press("KEY_DOWN")
       sleep(3)
       press("KEY_OK")
       sleep(3)
       #texts=getText(470,365,800,420)'''
       texts=text_read()
       '''passMessageWith_Screenshots(texts)'''
       fh = open("test.txt","wb")
       fh.write(texts)
       fh.close()
                         
    except Exception:
        assert False, failMessage("Error occured : %s" %e.args)

def test_nbc_sports_validate():
    from time import sleep
    try:
        func_name = sys._getframe().f_code.co_name
        test='tests/'+os.path.basename(__file__)+'::'+func_name
        sleep(5)
        
        """Validating Authentication"""        
        wait_for_motion_detect(mask=None,timeout_secs=15)
        passMessageWith_Screenshots("Authenticated Successfully")
        press("KEY_BACK")
        sleep(3)
        
        """Validate filter option in Live & Upcoming"""        
        filter_section()
        
        """Validate video options in Replay section"""
        
        """Validate filter option in Replay"""
        key_press("KEY_UP","UP")
        sleep(2)
        key_press("KEY_RIGHT","RIGHT")
        sleep(2)
        key_press("KEY_DOWN","DOWN")
        sleep(2)
        filter_section()
        
        """Validate video options in Replay section"""
        #press("KEY_RIGHT")
        #sleep(5)
        press("KEY_OK")
        sleep(5)
        if match("images/Nbc_Sports_app/nbc_sports_player_error.png"):
                    sleep(2)
                    infoMessageWith_Screenshots ("Video Unavailable")
                    press("KEY_OK")
                    sleep(5)
        else:
             infoMessageWith_Screenshots ("Replay video is selected")
             sleep(5)
             if match("images/Nbc_Sports_app/nbc_sports_replay_resume.png"):
                    sleep(2)
                    press("KEY_OK")
                    infoMessageWith_Screenshots ("Resume button is clicked")
                    sleep(5)
             else:
                 infoMessageWith_Screenshots ("Replay video is selected")
             
             wait_for_motion_detect(mask=None,timeout_secs=20)
      
             """Motion Detection after Pressing the Pause Button """      
             key_press("KEY_PLAYPAUSE","'Pause'")
             sleep(2)
             try:
                 wait_for_motion(mask=None,timeout_secs=5)
                 failMessageWith_Screenshots("Motion detected - after clicking the Pause Button")
             except Exception,e:  
              passMessageWith_Screenshots("Motion Not detected - after clicking the Pause Button")
                
             """Fast Forward Video """ 
             press("KEY_RIGHT")
             sleep(2)
             startTime_before = getText(107,573,220,603)
             print startTime_before
             key_press("KEY_FASTFORWARD","Fast Forward")        
             startTime_before=startTime_before.replace(")","")
             startTime_before=startTime_before.replace("L","1")
             startTime_before=startTime_before.replace("O","0")
             sleep(3)
      
             """Verify Video is Fast Forwarded or not"""      
             key_press("KEY_PLAYPAUSE","'Play'")
             sleep(2)
             press("KEY_RIGHT")
             sleep(2)
             startTime_after = getText(107,573,220,603)
             startTime_after=startTime_after.replace(")","")
             startTime_after=startTime_after.replace("L","1")
             startTime_after=startTime_after.replace("O","0")
             if startTime_after > startTime_before:
                 passMessageWith_Screenshots("Video is fast forwarded from "+startTime_before+" to "+startTime_after)
             else:
                 failMessageWith_Screenshots("Video is not fast forwarded")
      
             """Rewind Video"""
             #key_press("KEY_RIGHT","'Pause'")
             sleep(1)
             startTime_before =getText(107,573,220,603)
             key_press("KEY_REWIND","Rewind")
             startTime_before=startTime_before.replace(")","")
             startTime_before=startTime_before.replace("L","1")
             startTime_before=startTime_before.replace("O","0")
             sleep(2)
      
             """Verify Video is Rewinded or not"""
             key_press("KEY_PLAYPAUSE","Play Button")
             sleep(2)
             press("KEY_RIGHT")
             sleep(2)
             startTime_after =getText(107,573,220,603)
             startTime_after=startTime_after.replace(")","")
             startTime_after=startTime_after.replace("L","1")
             startTime_after=startTime_after.replace("O","0")
             if startTime_after < startTime_before:
                 passMessageWith_Screenshots("Video is rewound from "+startTime_before+" to "+startTime_after)
             else:
                 failMessageWith_Screenshots("Video rewind Failed")
             key_press("KEY_PLAYPAUSE","'Play'")
             sleep(3)
             press("KEY_INFO")
             sleep(2)
             if match("images/Nbc_Sports_app/nbc_sports_no_caption.png"):
                    infoMessageWith_Screenshots ("No caption is available for the video")
                    sleep(2)
                    press("KEY_INFO")
                    sleep(2)
             else:
                 press("KEY_INFO")
                 sleep(2)
                 for _ in range(100):
                     cc = getText(250,155,950,220)
                     if (len(cc.strip()) >= 10) : 
                         passMessageWith_Screenshots ("Closed Caption is displayed in the video as===> "+cc)
                         break
        
        """Validate filter option in Highlights"""
        key_press("KEY_BACK","Back")
        sleep(2)
        key_press("KEY_UP","UP")
        sleep(2)
        key_press("KEY_RIGHT","RIGHT")
        sleep(2)
        key_press("KEY_DOWN","DOWN")
        sleep(2)
        filter_section() 
        press("KEY_INFO")
        sleep(3)
        press("KEY_DOWN")
        sleep(3)
        press("KEY_OK")
        sleep(3)
        press("KEY_INFO")
        sleep(3)  
        nbc_sports_show_count()
        press("KEY_INFO")
        sleep(3)
        press("KEY_OK")
        sleep(3)
        press("KEY_DOWN")
        sleep(3)
        press("KEY_INFO")
        sleep(3)
        
        """Validate video options in Highlights section"""
        press("KEY_RIGHT")
        sleep(5)
        press("KEY_OK")
        sleep(5)
        if match("images/Nbc_Sports_app/nbc_sports_player_error.png"):
                    sleep(2)
                    infoMessageWith_Screenshots ("Video Unavailable")
                    press("KEY_OK")
                    sleep(5)
        else:
             infoMessage ("Video is selected")
             if match("images/Nbc_Sports_app/nbc_sports_resume.png"):
                    sleep(2)                    
                    press("KEY_OK")
                    infoMessageWith_Screenshots ("Resume button is clicked")
                    sleep(5)
             else:
                 infoMessageWith_Screenshots ("Video is selected")
             
             wait_for_motion_detect(mask=None,timeout_secs=20)
      
             """Motion Detection after Pressing the Pause Button """      
             key_press("KEY_PLAYPAUSE","'Pause'")
             sleep(2)
             try:
                 wait_for_motion(mask=None,timeout_secs=5)
                 failMessageWith_ScreenshotsMessage("Motion detected - after clicking the Pause Button")
             except Exception,e:  
              passMessageWith_Screenshots("Motion Not detected - after clicking the Pause Button")
        
             """Fast Forward Video """ 
             press("KEY_RIGHT")
             sleep(2)
             startTime_before = getText(107,573,220,603)
             print startTime_before
             key_press("KEY_FASTFORWARD","Fast Forward")        
             startTime_before=startTime_before.replace(")","")
             startTime_before=startTime_before.replace("L","1")
             startTime_before=startTime_before.replace("O","0")
             sleep(3)
      
             """Verify Video is Fast Forwarded or not"""      
             key_press("KEY_PLAYPAUSE","'Play'")
             sleep(2)
             press("KEY_RIGHT")
             sleep(2)
             startTime_after = getText(107,573,220,603)
             startTime_after=startTime_after.replace(")","")
             startTime_after=startTime_after.replace("L","1")
             startTime_after=startTime_after.replace("O","0")
             if startTime_after > startTime_before:
                 passMessageWith_Screenshots("Video is fast forwarded from "+startTime_before+" to "+startTime_after)
             else:
                 failMessageWith_Screenshots("Video is not fast forwarded")
      
             """Rewind Video"""
             #key_press("KEY_RIGHT","'Pause'")
             sleep(1)
             startTime_before =getText(107,573,220,603)
             key_press("KEY_REWIND","Rewind")
             startTime_before=startTime_before.replace(")","")
             startTime_before=startTime_before.replace("L","1")
             startTime_before=startTime_before.replace("O","0")
             sleep(2)
      
             """Verify Video is Rewinded or not"""
             key_press("KEY_PLAYPAUSE","Play Button")
             sleep(2)
             press("KEY_RIGHT")
             sleep(2)
             startTime_after =getText(107,573,220,603)
             startTime_after=startTime_after.replace(")","")
             startTime_after=startTime_after.replace("L","1")
             startTime_after=startTime_after.replace("O","0")
             if startTime_after < startTime_before:
                 passMessageWith_Screenshots("Video is rewound from "+startTime_before+" to "+startTime_after)
             else:
                 failMessageWith_Screenshots("Video rewind failed")
             key_press("KEY_PLAYPAUSE","'Play'")
             sleep(3)
             press("KEY_INFO")
             sleep(2)
             infoMessageWith_Screenshots ("Info button is clicked")
             if match("images/Nbc_Sports_app/nbc_sports_no_caption.png"):
                    infoMessageWith_Screenshots ("No caption is available for the video")
                    sleep(2)
                    press("KEY_INFO")
                    sleep(2)
             else:
                 press("KEY_INFO")
                 sleep(2)
                 for _ in range(100):
                     cc = getText(250,155,950,220)
                     if (len(cc.strip()) >= 10) : 
                         passMessageWith_Screenshots ("Closed Caption is displayed in the video as===> "+cc)
                         break
                     
                     
        """Validate settings screen and menu availability options """
        key_press("KEY_BACK","Back")
        sleep(2)
        key_press("KEY_UP","UP")
        sleep(2)
        key_press("KEY_RIGHT","Right")
        sleep(2)
        if match("images/Nbc_Sports_app/nbc_sports_settings.png"):
            passMessageWith_Screenshots ("Settings screen is displayed")
            press("KEY_DOWN")
            sleep(3)
            if match("images/Nbc_Sports_app/nbc_sports_caption_menu.png"):
                passMessageWith_Screenshots ("Caption menu screen is displayed")
            else :
                failMessageWith_Screenshots("Caption menu screen is not displayed")
            press("KEY_DOWN")
            sleep(3)
            if match("images/Nbc_Sports_app/nbc_sports_termofuse_menu.png"):
                passMessageWith_Screenshots ("Terms of Use menu screen is displayed")
            else :
                failMessageWith_Screenshots("Terms of Use menu screen is not displayed")
            press("KEY_DOWN")
            sleep(3)
            if match("images/Nbc_Sports_app/nbc_sports_privacypolicy_menu.png"):
                passMessageWith_Screenshots ("Privacy Policy menu screen is displayed")
            else :
                failMessageWith_Screenshots("Privacy Policy menu screen is not displayed")
            press("KEY_DOWN")
            sleep(3)
            if match("images/Nbc_Sports_app/nbc_sport_help_menu.png"):
                passMessageWith_Screenshots ("Help and Support menu Screen is displayed")
            else :
                failMessageWith_Screenshots("Help and Support menu Screen is not displayed")
            press("KEY_DOWN")
            sleep(3)
            if match("images/Nbc_Sports_app/nbc_sports_about.png"):
                passMessageWith_Screenshots ("About menu screen is displayed")
            else :
                failMessageWith_Screenshots("About menu screen is not displayed")
            press("KEY_DOWN")
            sleep(3)
            if match("images/Nbc_Sports_app/nbc_sports_signout_menu.png"):
                passMessageWith_Screenshots ("Sign Out menu Screen is displayed")
                key_press("KEY_RIGHT","Right")
                sleep(2)
                key_press("KEY_OK","OK")
                sleep(2)
                key_press("KEY_RIGHT","Right")
                sleep(2)
                key_press("KEY_OK","OK")
                sleep(2)
                if match("images/Nbc_Sports_app/nbc_sports_signout_successful.png"):
                    passMessageWith_Screenshots ("Sign out is successful")
            else :
                failMessageWith_Screenshots("Sign out menu Screen is not displayed")
        else:
            failMessageWith_Screenshots("Settings Screen is not displayed")            
        """Navigate to Roku Home"""
        to_roku_home()     
       
    except Exception,e:
        assert False, failMessage("Error occured : %s" %e.args)
        
    
def filter_section() :
    from time import sleep
    try:
        press("KEY_INFO")
        sleep(3)
        flag=0
        
        if match("images/Nbc_Sports_app/nbs_sports_filter_menu.png"):
            sleep(2)
            passMessageWith_Screenshots ("Filter  option is displayed at the left side of the screen")
            press("KEY_DOWN")
            sleep(3)
            press("KEY_OK")
            sleep(3)
            filter=getText(85,195,165,230)
            print filter
            passMessageWith_Screenshots(filter+" filter is selected")
            if match("images/Nbc_Sports_app/nbc_sports_no_filter.png"):
                infoMessageWith_Screenshots ("No video is listed in the selected filter")
                flag=1
                press("KEY_INFO")
                sleep(3)
                press("KEY_INFO")
                sleep(3)
                press("KEY_UP")
                sleep(3)
                press("KEY_OK")
                sleep(3)
            press("KEY_INFO")
            sleep(3)
        else:
             infoMessageWith_Screenshots ("Filter  option is not displayed at the left side of the screen") 
        
        if flag==0:
            passMessageWith_Screenshots ("Screen is loaded with selected filter video list")
            count=0
            for _ in range(8):
                if match("images/Nbc_Sports_app/nbc_sports_selected_clip.png"):
                    result=match("images/Nbc_Sports_app/nbc_sports_selected_clip.png" ,region=Region.ALL)
                    print result.region.x
                    print result.region.y
                    print result.region.right
                    print result.region.bottom
                    x=result.region.x
                    y=310
                    right=result.region.right
                    right=x+70
                    bottom=340
                    if match("images/Nbc_Sports_app/nbc_sports_filter_clip.png" ,region=Region(x=result.region.x,y=285,right=result.region.right,bottom=result.region.bottom)):
                        
                        #ep=getText(x,y,right,bottom)
                        ep=x
                        if count==ep:
                            break
                        else :
                            count=ep
                            press("KEY_RIGHT")
                            sleep(4)
               
            else:
                assert False, failMessageWith_Screenshots("Video name is not showing")
            passMessageWith_Screenshots ("Shows Filtered based on the Filter")
            press("KEY_INFO")
            sleep(3)
            press("KEY_UP")
            sleep(3)
            press("KEY_OK")
            sleep(3)
            press("KEY_INFO")
            sleep(3)
    except Exception,e:
        assert False, failMessage("Error occured : %s" %e.args)
                
def test_XFINITY_APP_LIVE_TV():    
    try:
      func_name = sys._getframe().f_code.co_name
      test='tests/'+os.path.basename(__file__)+'::'+func_name
          
      """Navigating to Roku Home Screen"""      
      to_roku_home()
      press("KEY_OK")     
      sleep(4)
      
      """Opening XFINITY App"""      
      press_until_match("KEY_DOWN", "images/Xfinity_app/xfinity_app.png")
      if match("images/Xfinity_app/xfinity_SelectedImage.png"):
                    sleep(2)
                    press("KEY_OK")
      else:         
        navigate_to_tile("images/Xfinity_app/xfinity_app.png")
        press("KEY_OK")     
      infoMessage ("Clicked on XFINITY App")
      
      wait_for_element("images/Xfinity_app/xfinity_menu.png","Menu Icon in XFINITY Home Page",timeout_secs=20)
     
      """Navigating to Live TV Channels Section"""
      press("KEY_OK")
      wait_for_element("images/Xfinity_app/xfinity_LiveHome.png","Tv Listing in Live TV Page",timeout_secs=20)
      
      start = time.time()
      if(is_screen_black(mask="images/Xfinity_app/bsmask.png")):
            sleep(3)
            wait_until(lambda: not is_screen_black(mask="images/Xfinity_app/bsmask.png"),timeout_secs=20)
            end = time.time()
            infoMessageWith_Screenshots("Black Screen detected for %s seconds" %str(end-start))
      else:
        infoMessageWith_Screenshots("No black screen")
      
      wait_for_element("images/Xfinity_app/xfinity_tvListingapp.png","Tv Listing in Live TV Page",timeout_secs=20)
      press("KEY_DOWN")
      sleep(2)
      press("KEY_LEFT")
      sleep(2)
      press_until_match("KEY_DOWN", "images/Xfinity_app/MEApp.png")
      if match("images/Xfinity_app/METVAppSelected.png"):
                                       
                press("KEY_OK")
      else:         
        navigate_to_tile("images/Xfinity_app/METVAppSelected.png")
        #press("KEY_LEFT")
        sleep(2)
        press("KEY_OK")     
      
      wait_for_element("images/Xfinity_app/watchbutton.png","Watch Button in TV listing page",timeout_secs=20)
      for _ in range(100):
                str2 = getText(204,438,589,482)
                if str2.strip():
                     infoMessageWith_Screenshots ("Selected video is  -->"+str2)
                     break
      else:
        assert False, failMessageWith_Screenshots("Video Name is not showing")
    
      for _ in range(100):
                str1 = getText(903,460,1038,486)
                if str1.strip():
                     passMessageWith_Screenshots ("Details of the video is --> "+str1)
                     break
      else:
        failMessageWith_Screenshots("Details of the video is not showing")
      """Verify Closed Caption is displaying or not"""
      
      if match("images/Xfinity_app/closedcaption.png"):
          
          press("KEY_OK")
          if wait_until(lambda: match("images/Xfinity_app/buffering.png"),timeout_secs=5):
             passMessageWith_Screenshots("Video is buffering")
          else:
             infoMessageWith_Screenshots("Video is not buffering") 
          sleep(5)
          if wait_until(lambda: match("images/Xfinity_app/METVLogo.png",match_parameters=stbt.MatchParameters(match_method="ccoeff-normed", confirm_method="none")),timeout_secs=10):
            wait_for_motion_detect(mask=None,timeout_secs=15)
          else:
            #infoMessageWith_Screenshots ("Advertisement is playing")  
            #wait_for_element("images/Xfinity_app/METVLogo.png","METV Logo in Video Player",timeout_secs=20)  
            wait_for_motion_detect(mask=None,timeout_secs=15)
          sleep(15)
          for _ in range(200):
                str2 = getText(284,620,915,653)
                print str2
                if str2.strip():
                   passMessageWith_Screenshots ("Closed Caption is displayed in the video as "+str2)
                   break
          else:
            assert False, failMessageWith_Screenshots("Closed Caption is not displayed in the video")
      else:
        sleep(5)
        press("KEY_OK")
        if wait_until(lambda: match("images/Xfinity_app/buffering.png"),timeout_secs=5):
             passMessageWith_Screenshots("Video is buffering")
        else:
             infoMessageWith_Screenshots("Video is not buffering")  
        sleep(5)
        if wait_until(lambda: match("images/Xfinity_app/METVLogo.png",match_parameters=stbt.MatchParameters(match_method="ccoeff-normed", confirm_method="none")),timeout_secs=10):
            #infoMessageWith_Screenshots ("Advertisement is not playing")
            wait_for_motion_detect(mask=None,timeout_secs=15)
        else:
            #infoMessageWith_Screenshots ("Advertisement is playing")  
            wait_for_motion_detect(mask=None,timeout_secs=15)
            #wait_for_element("images/Xfinity_app/METVLogo.png","METV Logo in Video Player",timeout_secs=20)  
        #wait_for_motion_detect(mask=None,timeout_secs=15)
        
      
      """Navigating to Roku Home Screen"""
      
      to_roku_home()
   
    except Exception,e:
        assert False, failMessage("Error occured : %s" %e.args)
 
 
def test_XFINITY_APP_VOD():
    try:
      func_name = sys._getframe().f_code.co_name
      test='tests/'+os.path.basename(__file__)+'::'+func_name
          
      """Navigating to Roku Home Screen"""
      
      to_roku_home()
      press("KEY_OK")     
      sleep(4)
      
      """Opening XFINITY App"""
      
      press_until_match("KEY_DOWN", "images/Xfinity_app/xfinity_app.png")
      if match("images/Xfinity_app/xfinity_SelectedImage.png"):
                    sleep(2)
                    press("KEY_OK")
      else:         
        navigate_to_tile("images/Xfinity_app/xfinity_app.png")
        press("KEY_OK")     
      infoMessage ("Clicked on XFINITY App")
      
      wait_for_element("images/Xfinity_app/xfinity_menu.png","Menu Icon in XFINITY Home Page",timeout_secs=20) 
      press("KEY_LEFT")
      key_press("KEY_OK","Browse")
      wait_for_element("images/Xfinity_app/optionsIcon.png","Options Icon in Browse Page",timeout_secs=20)
      
      key_press("KEY_RIGHT","Movies")
      start = time.time()
      if(is_screen_black(mask="images/Xfinity_app/bsmask.png")):
            sleep(3)
            wait_until(lambda: not is_screen_black(mask="images/Xfinity_app/bsmask.png"),timeout_secs=20)
            end = time.time()
            infoMessageWith_Screenshots("Black Screen detected for %s seconds" %str(end-start))
      else:
        infoMessageWith_Screenshots("No black screen")
      wait_for_element("images/Xfinity_app/feturesText.png","Featured Section in Movies Page",timeout_secs=20) 
      press("KEY_DOWN")
      sleep(2)
      press("KEY_RIGHT")
      sleep(2)
      key_press("KEY_OK","Second Movie in Featured section")
      wait_for_element("images/Xfinity_app/peoplealsowatchedText.png","Selected Movie details Page",timeout_secs=20) 
      infoMessageWith_Screenshots("The Movie selected is "+getText(296,109,919,177))
      infoMessageWith_Screenshots("The Details of the movie selected ===> "+getText(986,208,1103,228))
      if match("images/Xfinity_app/resume_button.png"):
                    press("KEY_RIGHT")
                    key_press("KEY_OK","Start Over")
      else:         
        key_press("KEY_OK","Watch")
      sleep(2)
      if match("images/Xfinity_app/ccOption.png"):
          isCC='true'
      key_press("KEY_OK","Play Button")
      if wait_until(lambda: match("images/Xfinity_app/buffering.png"),timeout_secs=5):
             passMessageWith_Screenshots("Video is buffering")
      else:
        infoMessageWith_Screenshots("Video is not buffering")
      wait_for_element("images/Xfinity_app/pause_button.png","Video play",timeout_secs=20)
      if wait_until(lambda: match("images/Xfinity_app/XfinityAd.png",match_parameters=stbt.MatchParameters(match_method="ccoeff-normed", confirm_method="none")),timeout_secs=5):
        infoMessageWith_Screenshots ("Advertisement is playing")
      else:
        infoMessageWith_Screenshots ("Advertisement is not playing")
      """Motion detection"""
      
      wait_for_motion_detect(mask=None,timeout_secs=15)
      
      """Motion Detection after Pressing the Pause Button """
      key_press("KEY_PLAYPAUSE","'Pause'")
      try:
          wait_for_motion(mask=None,timeout_secs=5)
          failMessage("Motion detected - after clicking the Pause Button")
      except Exception,e:  
              passMessageWith_Screenshots("Motion Not detected - after clicking the Pause Button")
      
      """Fast Forward Video """ 
      
      startTime_before = getText(64,534,131,553)
      press("KEY_FASTFORWARD")
      press("KEY_FASTFORWARD")
      key_press("KEY_FASTFORWARD","Fast Forward")
      
      startTime_before=startTime_before.replace(")","")
      startTime_before=startTime_before.replace("L","1")
      startTime_before=startTime_before.replace("O","0")
      sleep(8)
      
      """Verify Video is Fast Forwarded or not"""
      
      key_press("KEY_PLAYPAUSE","'Play'")
      sleep(2)
      startTime_after = getText(64,534,131,553)
      startTime_after=startTime_after.replace(")","")
      startTime_after=startTime_after.replace("L","1")
      startTime_after=startTime_after.replace("O","0")
      if startTime_after > startTime_before:
              passMessageWith_Screenshots("Video is Fast forwarded from "+startTime_before+" to "+startTime_after)
      else:
         failMessageWith_Screenshots("Video is not Fast forwarded")
      
      """Rewind Video"""
      key_press("KEY_PLAYPAUSE","'Pause'")
      sleep(1)
      startTime_before =getText(64,534,131,553)
      key_press("KEY_REWIND","Rewind")
      
      
      startTime_before=startTime_before.replace(")","")
      startTime_before=startTime_before.replace("L","1")
      startTime_before=startTime_before.replace("O","0")
      sleep(2)
      
      """Verify Video is Rewinded or not"""
                   
      key_press("KEY_PLAYPAUSE","Play Button")
      sleep(2)
      startTime_after =getText(64,534,131,553)
      startTime_after=startTime_after.replace(")","")
      startTime_after=startTime_after.replace("L","1")
      startTime_after=startTime_after.replace("O","0")
      if startTime_after < startTime_before:
              passMessageWith_Screenshots("Video is Rewound from "+startTime_before+" to "+startTime_after)
      else:
         failMessageWith_Screenshots("Video Rewind Failed")
         
      """Closed Caption Verification"""
      sleep(3)
      for _ in range(100):
                cc = getText(400,621,843,660)
                if cc.strip():
                     passMessageWith_Screenshots ("Closed Caption is displayed in the video as===> "+cc)
                     break
      else:
        assert False, failMessageWith_Screenshots("Closed Caption is not displayed in the video")  
        
      key_press("KEY_PLAYPAUSE","Pause Button")  
      progress_time =getText(64,534,131,553)  
      
      """Navigating to Roku Home Screen"""
      
      to_roku_home() 
      press("KEY_OK")     
      sleep(4)
      
      """Opening XFINITY App"""
      
      press_until_match("KEY_DOWN", "images/Xfinity_app/xfinity_app.png")
      if match("images/Xfinity_app/xfinity_SelectedImage.png"):
                    sleep(2)
                    press("KEY_OK")
      else:         
        navigate_to_tile("images/Xfinity_app/xfinity_app.png")
        press("KEY_OK")     
      infoMessage ("Clicked on XFINITY App")
      
      wait_for_element("images/Xfinity_app/xfinity_menu.png","Menu Icon in XFINITY Home Page",timeout_secs=20) 
      press("KEY_LEFT")
      key_press("KEY_OK","Browse")
      wait_for_element("images/Xfinity_app/optionsIcon.png","Options Icon in Browse Page",timeout_secs=20)
      
      key_press("KEY_RIGHT","Movies")
      
      wait_for_element("images/Xfinity_app/feturesText.png","Featured Section in Movies Page",timeout_secs=20) 
      press("KEY_DOWN")
      sleep(2)
      press("KEY_RIGHT")
      sleep(2)
      key_press("KEY_OK","First Movie in Featured section")
      wait_for_element("images/Xfinity_app/peoplealsowatchedText.png","Selected Movie details Page",timeout_secs=20)
      wait_for_element("images/Xfinity_app/resume_button.png","Resume button",timeout_secs=20)
      key_press("KEY_OK","Resume")
      
      wait_for_element("images/Xfinity_app/pause_button.png","Video play",timeout_secs=20)
      sleep(3)
      key_press("KEY_PLAYPAUSE","Pause Button")
      sleep(2)
      resume_time =getText(64,534,131,553) 
      if progress_time <= resume_time:
              passMessage("Video is resumed")
      else:
         failMessage("Video resume failed") 
      to_roku_home()  
    except Exception,e:
        assert False, failMessage("Error occured : %s" %e.args) 
        
def test_NBC_APP():
    try:
      func_name = sys._getframe().f_code.co_name
      test='tests/'+os.path.basename(__file__)+'::'+func_name
          
      """Navigating to Roku Home Screen"""
      
      to_roku_home()
      press("KEY_OK")     
      sleep(4)
      
      """Opening NBC App"""
      
      press_until_match("KEY_DOWN", "images/Nbc_app/nbc_app.png")
      if match("images/Nbc_app/nbc_SelectedImage.png"):
                    sleep(2)
                    press("KEY_OK")
      else:         
        navigate_to_tile("images/Nbc_app/nbc_app.png")
        press("KEY_OK")     
      infoMessage ("Clicked on NBC App")
      
      wait_for_element("images/Nbc_app/nbc_menu.png","Menu Icon in NBC Home Page",timeout_secs=20)
     
      """Navigating to Continue watching Section"""
     
      press("KEY_DOWN")
      sleep(5)
      
      
      """Open the Show which is passing from CSV file"""
      
      open_Show(test,"images/Nbc_app/nbc_selectbar.png") 
           
      """Playing the Video"""
      
      sleep(3)
      press("KEY_RIGHT")
      sleep(1)
      key_press("KEY_OK","Start")
      
      wait_for_element("images/Nbc_app/nbclogo_onplayer.png","NBC Logo in Video Player",timeout_secs=35)
   
      sleep(2)
      key_press("KEY_INFO","Info")
      wait_for_element("images/Nbc_app/nbc_video_captions.png","NBC Video Captions Popup",timeout_secs=10)
      
      """Advertisement Verification """
      
      add_verification_NBC()
      
      """Motion detection"""
      
      wait_for_motion_detect(mask=None,timeout_secs=15)
      
      """Motion Detection after Pressing the Pause Button """
      
      key_press("KEY_INFO","Info")
      sleep(2)
      key_press("KEY_PLAYPAUSE","'Pause'")
      try:
          wait_for_motion(mask=None,timeout_secs=5)
          failMessage("Motion detected - after clicking the Pause Button")
      except Exception,e:  
              passMessage("Motion Not detected - after clicking the Pause Button")
     
      
      """Closed Caption ON"""
      
      key_press("KEY_INFO","'Info'")
      wait_for_element("images/Nbc_app/nbc_video_captions.png","NBC Video Captions Popup",timeout_secs=20)
      press("KEY_RIGHT")
      key_press("KEY_OK","'CC ON'")
      
      """Verify Closed Caption is displaying or not"""
      
      key_press("KEY_PLAYPAUSE","'Play'")
      sleep(15)
      for _ in range(100):
                cc = getText(233,442,1041,531)
                if cc.strip():
                     passMessageWith_Screenshots ("Closed Caption is displayed in the video as "+cc)
                     break
      else:
        assert False, failMessageWith_Screenshots("Closed Caption is not displayed in the video")
    
      """Fast Forwarding the Video"""
                         
      key_press("KEY_PLAYPAUSE","'Pause'")
      press("KEY_FASTFORWARD")
      wait_for_element("images/Nbc_app/nbc_frwd.png","Fast Forward Icon",timeout_secs=20)
      
      startTime_before = getText(176,528,243,553)
      startTime_before=startTime_before.replace(")","")
      startTime_before=startTime_before.replace("L","1")
      startTime_before=startTime_before.replace("O","0")
      sleep(6)
      
      """Verify Video is Fast Forwarded or not"""
      
      key_press("KEY_PLAYPAUSE","'Play'")
      startTime_after = getText(176,528,243,553)
      startTime_after=startTime_after.replace(")","")
      startTime_after=startTime_after.replace("L","1")
      startTime_after=startTime_after.replace("O","0")
      if startTime_after > startTime_before:
              passMessage("Video is Fast forwarded from "+startTime_before+" to "+startTime_after)
      else:
         failMessage("Video is not Fast forwarded")
      
      """Rewind the Video"""
    
      key_press("KEY_PLAYPAUSE","'Pause'")
      press("KEY_REWIND")
      wait_for_element("images/Nbc_app/nbc_rwd.png","Rewind Icon",timeout_secs=20)
      
      startTime_before =getText(176,528,243,553)
      startTime_before=startTime_before.replace(")","")
      startTime_before=startTime_before.replace("L","1")
      startTime_before=startTime_before.replace("O","0")
      sleep(3)
      
      """Verify Video is Rewinded or not"""
                   
      key_press("KEY_PLAYPAUSE","Play Button")
      startTime_after =getText(176,528,243,553)
      startTime_after=startTime_after.replace(")","")
      startTime_after=startTime_after.replace("L","1")
      startTime_after=startTime_after.replace("O","0")
      if startTime_after < startTime_before:
              passMessage("Video is Rewinded from "+startTime_before+" to "+startTime_after)
      else:
         failMessage("Video is not Rewinded - StartTime="+startTime_before+", EndTime="+startTime_after)
      key_press("KEY_PLAYPAUSE","Pause Button")
      
      """Navigating to Roku Home Screen"""
      
      to_roku_home()
   
    except Exception,e:
        assert False, failMessage("Error occured : %s" %e.args) 
       
def test_NBC_App_New():
    try:
      func_name = sys._getframe().f_code.co_name
      test='tests/'+os.path.basename(__file__)+'::'+func_name
          
      """Navigating to Roku Home Screen"""
      
      to_roku_home()
      press("KEY_OK")     
      sleep(4)
      
      """Opening NBC App"""
      
      press_until_match("KEY_DOWN", "images/Nbc_app/nbc_app.png")
      if match("images/Nbc_app/nbc_SelectedImage.png"):
                    sleep(2)
                    press("KEY_OK")
      else:         
        navigate_to_tile("images/Nbc_app/nbc_app.png")
        press("KEY_OK")     
      infoMessage ("Clicked on NBC App") 
      if match("images/Nbc_app/BufferIcon.png.png"):
           print "no buffer"        
      else: 
        sleep(1)          
        navigate_to_tile("images/Xfinity_app/xfinity_app.png")
    except Exception,e:
        assert False, failMessage("Error occured : %s" %e.args) 

def open_Show(test,image):
    for _ in range(3): 
            result=match(image ,region=Region.ALL)
            episode=getText(result.region.x,135,result.region.right,result.region.bottom)   
            if Read_Data_CSV(test,'Episode') in episode:
                        key_press("KEY_OK","Episode")
                        break
            else:   
                press("KEY_RIGHT") 
                sleep(3)   
            
    else:
        assert False, failMessageWith_Screenshots("The particular Episode is not displaying in the screen")

def add_verification_NBC():
    
    start=getText(176,528,243,553)
    
    sleep(3)
    end=getText(176,528,243,553)
    
    if start.replace('O', '0').split(':',len(start))[1] in end.replace('O', '0').split(':',len(end))[1]:
        infoMessageWith_Screenshots ("Advertisement is playing")
        
        wait_until(lambda: getText(176,528,243,553).replace('O', '0').split(':',len(end))[1] not in end.replace('O', '0').split(':',len(end))[1],
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
    code1= getText(470,365,800,420)
    code1=code1.replace(" ","")
    code=list(code1)
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
            result=match("images/Nbc_Sports_app/nbc_sports_S.png")
            x=result.region.x
            print result.region.x
            print result.region.y
            if(x>=480 and x<=520):
                code[0]='S'
            elif(x>=521 and x<=570):
                code[1]='S'
            elif(x>=571 and x<=605):
                code[2]='S'
            elif(x>=606 and x<=640):
                code[3]='S'
            elif(x>=641 and x<=690):
                code[4]='S'
            elif(x>=691 and x<=734):
                code[5]='S'
            elif(x>=735 and x<=802):
                code[6]='S'
    '''Set Z for equivalent image'''
    if match("images/Nbc_Sports_app/nbc_sports_Z.png"):
            result1=match("images/Nbc_Sports_app/nbc_sports_Z.png")
            x=result1.region.x
            print result1.region.x
            print result1.region.y
            if(x>=480 and x<=520):
                code[0]='Z'
            elif(x>=521 and x<=570):
                code[1]='Z'
            elif(x>=571 and x<=605):
                code[2]='Z'
            elif(x>=606 and x<=640):
                code[3]='Z'
            elif(x>=641 and x<=690):
                code[4]='Z'
            elif(x>=691 and x<=734):
                code[5]='Z'
            elif(x>=735 and x<=802):
                code[6]='Z'
    '''Set 2 for equivalent image'''
    if  match("images/Nbc_Sports_app/nbc_sports_2.png"):
            result3=match("images/Nbc_Sports_app/nbc_sports_2.png")
            x=result3.region.x
            print result3.region.x
            print result3.region.y
            if(x>=480 and x<=520):
                code[0]='2'
            elif(x>=521 and x<=570):
                code[1]='2'
            elif(x>=571 and x<=605):
                code[2]='2'
            elif(x>=606 and x<=640):
                code[3]='2'
            elif(x>=641 and x<=690):
                code[4]='2'
            elif(x>=691 and x<=734):
                code[5]='2'
            elif(x>=735 and x<=802):
                code[6]='2'
    '''Set B for equivalent image'''
    if  match("images/Nbc_Sports_app/nbc_sports_B.png"):
            result4=match("images/Nbc_Sports_app/nbc_sports_B.png")
            x=result4.region.x
            print result4.region.x
            print result4.region.y 
            if(x>=480 and x<=520):
                code[0]='B'
            elif(x>=521 and x<=570):
                code[1]='B'
            elif(x>=571 and x<=605):
                code[2]='B'
            elif(x>=606 and x<=640):
                code[3]='B'
            elif(x>=641 and x<=690):
                code[4]='B'
            elif(x>=691 and x<=734):
                code[5]='B'
            elif(x>=735 and x<=802):
                code[6]='B'
    '''Set 8 for equivalent image'''            
    if  match("images/Nbc_Sports_app/nbc_sports_8.png"):
            result5=match("images/Nbc_Sports_app/nbc_sports_8.png")
            x=result5.region.x
            print result5.region.x
            print result5.region.y
            if(x>=480 and x<=520):
                code[0]='8'
            elif(x>=521 and x<=570):
                code[1]='8'
            elif(x>=571 and x<=605):
                code[2]='8'
            elif(x>=606 and x<=640):
                code[3]='8'
            elif(x>=641 and x<=690):
                code[4]='8'
            elif(x>=691 and x<=734):
                code[5]='8'
            elif(x>=735 and x<=802):
                code[6]='8'
    '''Set 5 for equivalent image'''
    if  match("images/Nbc_Sports_app/nbc_sports_5.png"):
            result5=match("images/Nbc_Sports_app/nbc_sports_5.png")
            x=result5.region.x
            print result5.region.x
            print result5.region.y
            if(x>=480 and x<=520):
                code[0]='5'
            elif(x>=521 and x<=570):
                code[1]='5'
            elif(x>=571 and x<=605):
                code[2]='5'
            elif(x>=606 and x<=640):
                code[3]='5'
            elif(x>=641 and x<=690):
                code[4]='5'
            elif(x>=691 and x<=734):
                code[5]='5'
            elif(x>=735 and x<=802):
                code[6]='5'
    '''Concatenate final code'''            
    code1=code[0]+code[1]+code[2]+code[3]+code[4]+code[5]+code[6]
    print code
    print code1
    return code1
