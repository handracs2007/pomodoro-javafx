set PATH_TO_FX_MODS=D:\Libs\javafx-jmods-15\windows
set PATH_TO_FX=D:\Libs\javafx-sdk-15\windows\lib

dir /s /b src\*.java > sources.txt & javac --module-path %PATH_TO_FX% -d mods/pomodoro @sources.txt & del sources.txt
jlink --module-path "%PATH_TO_FX_MODS%;mods" --add-modules pomodoro --output pomodoro