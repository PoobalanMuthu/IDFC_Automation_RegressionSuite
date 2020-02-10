reg add "HKCU\Software\Microsoft\Windows\CurrentVersion\Internet Settings" /v AutoConfigURL /t REG_SZ /d "http://wpad.ba.sda.corp.telstra.com/fn1-proxy.pac" /f
reg add "HKCU\Software\Microsoft\Windows\CurrentVersion\Internet Settings" /v ProxyEnable /t REG_DWORD /d 0 /f

start iexplore "https://telstra.com"
set SleepTime=2
Timeout /T %SleepTime% /NoBreak>NUL
Taskkill /IM "iexplore.exe" /F
