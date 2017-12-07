# jce_examples
JCE Example Provider

Aggregated a couple of online examples.

Package cipher implements a cipher - this could be packaged up into a .jar and then used (but you need a [signed certificate from Oracle](https://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/HowToImplAProvider.html#Step61)).

Provider package shows a provider that's then dynamically packaged at runtime.

MyJCE package is stock code without the personal cert.

Sources
- http://codeandme.blogspot.com/2013/06/writing-your-own-jca-extensions-simple.html
- https://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/HowToImplAProvider.html
