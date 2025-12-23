@echo off
echo Stopping existing backend processes...
for /f "tokens=5" %%a in ('netstat -aon ^| findstr :8080 ^| findstr LISTENING') do (
    echo Killing process %%a
    taskkill /PID %%a /F
)

echo Waiting for port to be released...
timeout /t 2 /nobreak >nul

echo Starting backend...
cd /d %~dp0
call mvn clean spring-boot:run

pause

