package com.beenvip.shedu.http.okhttp;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * 自定义加载证书
 * 实现双向认证
 */
public class SSLCustomSocketFactory {
    /***
     * 双向认证
     * @param p12keyStore
     * @param p12keyPwd
     * @param trustKeyStore
     * @return
     */
    public static SSLSocketFactory getSocketFactory(InputStream p12keyStore, String p12keyPwd, InputStream trustKeyStore) {
        SSLContext sslcontext = null;
        KeyStore p12Keystore = null;
        KeyStore trustKS=null;
        try {
            p12Keystore = KeyStore.getInstance("PKCS12");
            p12Keystore.load(p12keyStore, p12keyPwd.toCharArray());
            trustKS= KeyStore.getInstance(KeyStore.getDefaultType());
            trustKS.load(null, null);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(p12Keystore, p12keyPwd.toCharArray());

            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(trustKeyStore);
            trustKS.setCertificateEntry(certificate.getSubjectX500Principal().getName(), certificate);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustKS);
            sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }  catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sslcontext.getSocketFactory();
    }

    /***
     * 单项认证
     * @param trustKeyStore
     * @param trustPwd
     * @return
     */
    public static SSLSocketFactory getSocketFactory(InputStream trustKeyStore,String trustPwd) {
        SSLContext sslcontext = null;
        KeyStore trustKesStore=null;
        try {
            trustKesStore= KeyStore.getInstance("BKS");
            trustKesStore.load(trustKeyStore, trustPwd.toCharArray());
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
            trustManagerFactory.init(trustKesStore);
            sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, trustManagerFactory.getTrustManagers(), null);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sslcontext.getSocketFactory();
    }
}
