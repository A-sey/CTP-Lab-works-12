@echo off
rem set /p x="������ ��� ��� �������樨: " 
rem set /p par="������ ��ப� ��� ����᪠: " 
:M1
echo ��������, ������ �������:
echo c - compile, s - start, e - exit, d - delete
CHOICE /c csed
if %ERRORLEVEL%==1 javac *.java
if %ERRORLEVEL%==2 java FractalExplorer
if %ERRORLEVEL%==3 exit
if %ERRORLEVEL%==4 del /q *.class
echo.
echo.
GOTO M1