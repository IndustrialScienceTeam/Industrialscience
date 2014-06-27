@echo off
rmdir build\tmp /s /q
gradlew setupdecompworkspace
gradlew eclipse
PAUSE