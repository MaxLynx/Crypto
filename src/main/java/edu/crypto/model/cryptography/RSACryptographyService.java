package edu.crypto.model.cryptography;


public class RSACryptographyService extends FileSystemConfiguredCryptographyService{

    private int n = 1;
    private int e = 17;
    private int d = 1;

    @Override
    protected String encryptText(String source) {
        int m = convertTextToInt(source);
        int c = (int)Math.pow(m, e) % n;
        return ""+c;
    }

    @Override
    protected String decryptText(String source) {
        int c = Integer.parseInt(source);
        int m = (int)Math.pow(c, d) % n;
        return convertIntToText(m);
    }

    private int convertTextToInt(String text){
        return 1;
    }

    private String convertIntToText(int code){
        return "";
    }
}
