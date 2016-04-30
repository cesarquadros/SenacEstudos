;NSIS Modern User Interface
;Basic Example Script
;Written by Joost Verburg
;--------------------------------
;Interface do usuario, modo moderno.
CRCCheck on
XPStyle on
!include "MUI2.nsh"
!include InstallOptions.nsh

;--------------------------------
;General
VIAddVersionKey /LANG=${LANG_PortugueseBR} "Nome do produto" "Agenda Eletronix"
VIAddVersionKey /LANG=${LANG_PortugueseBR} "Empresa" "ninOX Solutions"
VIAddVersionKey /LANG=${LANG_PortugueseBR} "Versão" "00.01.00.00"

VIAddVersionKey /LANG=${LANG_ENGLISH} "ProductName" "ninOX Solutions"
VIAddVersionKey /LANG=${LANG_ENGLISH} "CompanyName" "ninOX Solutions"
VIAddVersionKey /LANG=${LANG_ENGLISH} "FileVersion" "1.0"
;---------------------------------------------------------------------------------
  ;Nome do arquivo
  Name "Agenda Eletronix"
  ;Nome do instalador
  OutFile "Instalador_Agenda Eletronix.exe"
  ;Icone a ser usado
  ;Icon "icone.ico"
  ;Versão do produto  
  VIProductVersion "00.01.00.00"
  SilentInstall Normal
  InstallDir "$PROGRAMFILES\ninOX Solutions\Agenda"
  ;pasta padrão de instalação (porém não é utilizado).
  ;InstallDir "$PROGRAMFILES\Apache Software Foundation\Tomcat 6.0\webapps"
  
  ;Get installation folder from registry if available
  ;InstallDirRegKey HKCU "Software\ServUni2 ""

  ;Requisita autorização no windows Vista
  RequestExecutionLevel admin
;--------------------------------
;Interface Settings
  !define MUI_ABORTWARNING  
 ; !define MUI_HEADERIMAGE
 ; !define MUI_HEADERIMAGE_BITMAP "${NSISDIR}\Contrib\Graphics\Header\logoSM.bmp" 



;--------------------------------
;páginas: 
  !insertmacro MUI_PAGE_WELCOME ;Bem vindo, instalador
  !insertmacro MUI_PAGE_COMPONENTS ;componentes
 ;!insertmacro MUI_PAGE_DIRECTORY ;diretorio onde vai ser instalad (não utilizado)
  !insertmacro MUI_PAGE_INSTFILES ;pagina onde instala os arquivos
  !insertmacro MUI_PAGE_FINISH ;pagina final, onde finaliza o instalador
   
 ; Paginas para o uninstall    
 !insertmacro MUI_UNPAGE_WELCOME 
 !insertmacro MUI_UNPAGE_CONFIRM
 !insertmacro MUI_UNPAGE_INSTFILES
 !insertmacro MUI_UNPAGE_FINISH
;--------------------------------
;Linguagem
  !insertmacro MUI_LANGUAGE "English"
  !insertmacro MUI_LANGUAGE "PortugueseBR"
;--------------------------------
;LicenseBkColor /gray ;cor de fundo da licença
ShowInstDetails Show
SetCompress Auto
SetDateSave on
SetDataBlockOptimize on
SetOverwrite on
;--------------------------------
;Função usada para aparecer a imagem ao começar a instalar.
;Function .onInit        
;        InitPluginsDir
;        File /oname=$PLUGINSDIR\splash.bmp "${NSISDIR}\Contrib\Graphics\Header\nsis.bmp"
;        #optional
;        #File /oname=$PLUGINSDIR\splash.wav "C:\myprog\sound.wav"
;        File /oname=$PLUGINSDIR\splash.bmp "${NSISDIR}\Contrib\Graphics\Wizard\sm4.bmp"
;        advsplash::show 1000 600 400 0x04025C $PLUGINSDIR\splash
;        Pop $0 
;        Delete $PLUGINSDIR\splash.bmp
;FunctionEnd
;função usada para criar a descrição de cada programa, na tela de componentes.
Function .onMouseOverSection
    FindWindow $R0 "#32770" "" $HWNDPARENT
    GetDlgItem $R0 $R0 1043 ; description item (must be added to the UI)

    StrCmp $0 0 "" +2
      SendMessage $R0 ${WM_SETTEXT} 0 "STR:SQL Express."

    StrCmp $0 1 "" +2
      SendMessage $R0 ${WM_SETTEXT} 0 "STR: Agenda de horários"
	
  FunctionEnd

;--------------------------------
;Installer Sections

;Section "SQL Express" SEC02 ;inicia uma seção e o nome dela
;	 SectionIn 1 2
;	 SetOutPath $PROGRAMFILES\temp1 ;onde vai extrair o arquivo.
;	 File "Firebird-2.5.5.26952_0_Win32.exe" ;o arquivo 
;	 ExecWait '"$PROGRAMFILES\temp1\Firebird-2.5.5.26952_0_Win32.exe"' ;comando que executa o .exe e mostra onde ele esta.
;	 RMDir /r "$PROGRAMFILES\temp1" ;apaga a pasta temp1.
;SectionEnd

Section "Instalar Agenda Eletronix 1.0" SEC03
	 SectionIn 1 3
		SetOutPath $PROGRAMFILES\ninOX Solutions\Agenda 
					File "Agenda.exe"
					File "icon.ico"
		SetOutPath $PROGRAMFILES\ninOX Solutions\Agenda\bd
					File "loginBD.txt"
					File "navalhinha.png"

	 ;SetOutPath $PROGRAMFILES\Gertec\Gerpin  
     ;File "Gerpin.exe"	 
	 WriteUninstaller "$INSTDIR\uninstall.exe"
	 CreateShortCut "$DESKTOP\Agenda.lnk" "$PROGRAMFILES\ninOX Solutions\Agenda\Agenda.exe" "$INSTDIR\icon.ico"
	 CreateDirectory "$SMPROGRAMS\Agenda"
	 CreateShortCut "$SMPROGRAMS\Agenda\Agenda.lnk" "$PROGRAMFILES\ninOX Solutions\Agenda\Agenda.exe" "$INSTDIR\icon.ico"
     CreateShortCut "$SMPROGRAMS\Agenda\Uninstall.lnk" "$INSTDIR\uninstall.exe"
SectionEnd

Section "Uninstall"

  ;ADD YOUR OWN FILES HERE...

  Delete "$INSTDIR\uninstall.exe"

  DeleteRegKey /ifempty HKCU "Software\Agenda1.0"
  
  delete "$DESKTOP\Agenda.lnk"
  
  RMDir /r "$PROGRAMFILES\ninOX Solutions\Agenda"
  RMDir /r "$SMPROGRAMS\ninOX Solutions\Agenda"

SectionEnd

;--------------------------------
;Descriptions
  ;Language strings
  LangString DESC_SecDummy ${LANG_ENGLISH} "Gertec"
  LangString DESC_SecDummy ${LANG_PortugueseBR} "Gertec"
  