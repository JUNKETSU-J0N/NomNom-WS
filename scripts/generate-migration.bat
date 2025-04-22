@echo off
setlocal enabledelayedexpansion

REM Konfiguration
set "SRC_FILE=target\generated-schema.sql"
set "MIGRATION_DIR=src\main\resources\db\migration"
set "PERCENT_THRESHOLD=90"

REM Optionaler Parameter für Threshold
if not "%1"=="" (
  set "PERCENT_THRESHOLD=%1"
)

REM Timestamp & Ziel-Dateiname
for /f %%A in ('powershell -Command "Get-Date -Format yyyyMMddHHmmss"') do set TIMESTAMP=%%A
set "NEW_FILE=V%TIMESTAMP%__auto_generated.sql"
set "DEST_FILE=%MIGRATION_DIR%\%NEW_FILE%"

REM Existiert die Quelldatei?
if not exist "%SRC_FILE%" (
  echo  Datei %SRC_FILE% nicht gefunden.
  exit /b 1
)

REM Letzte bestehende Migration laden (falls vorhanden)
set "LATEST_FILE="
for /f "delims=" %%F in ('dir /b /o:-n "%MIGRATION_DIR%\V*.sql" 2^>nul') do (
  set "LATEST_FILE=%%F"
  goto :compare
)

:compare
REM Wenn keine vorherige Migration existiert, direkt kopieren
if "%LATEST_FILE%"=="" (
  echo  Keine vorherige Migration gefunden – erstelle neue Migration: %NEW_FILE%
  copy "%SRC_FILE%" "%DEST_FILE%" >nul
  echo  Migration gespeichert unter %DEST_FILE%
  exit /b 0
)

REM Datei vergleichen (nur grob über Anzahl unterschiedlicher Zeilen)
set "OLD_FILE=%MIGRATION_DIR%\%LATEST_FILE%"
set "DIFF_COUNT=0"
set "TOTAL_LINES=0"

for /f "usebackq delims=" %%L in ("%SRC_FILE%") do (
  set /a TOTAL_LINES+=1
  findstr /x /c:"%%L" "%OLD_FILE%" >nul
  if errorlevel 1 (
    set /a DIFF_COUNT+=1
  )
)

REM Prozent berechnen
set /a "DIFF_PERCENT=(100 * DIFF_COUNT) / TOTAL_LINES"

if %DIFF_PERCENT% GEQ %PERCENT_THRESHOLD% (
  echo  %DIFF_PERCENT%% Unterschied erkannt – Migration wird erstellt: %NEW_FILE%
  copy "%SRC_FILE%" "%DEST_FILE%" >nul
  echo  Migration gespeichert unter %DEST_FILE%
) else (
  echo   Nur %DIFF_PERCENT%% Unterschied – Migration wird NICHT erstellt.
)

exit /b 0
