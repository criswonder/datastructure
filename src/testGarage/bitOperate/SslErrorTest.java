package testGarage.bitOperate;

public class SslErrorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SslError sslError = new SslError(3,new SslCertificate());
		sslError.addError(2);
		System.out.println(   sslError.hasError(SslError.SSL_IDMISMATCH) );
		System.out.println(   sslError.hasError(SslError.SSL_UNTRUSTED) );
	}

}
