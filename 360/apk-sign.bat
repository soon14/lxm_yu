set/p keystore_path=请输入.keystore的文件路径：
set/p alias_path=请输入keystore的alias：
set/p unsign_path=请输入待签名的apk文件路径：
set/p sign_path=请输入签名后生成的apk文件路径：

jarsigner -verbose -keystore %keystore_path% -signedjar %sign_path% %unsign_path% %alias_path%

pause