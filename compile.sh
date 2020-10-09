export PATH_TO_FX_MODS=/media/handra/DATA/Libs/javafx-jmods-15
javac --module-path $PATH_TO_FX_MODS -d mods/pomodoro $(find src/ -name "*.java")
jlink --module-path $PATH_TO_FX_MODS:mods --add-modules pomodoro --output pomodoro