capturedesk1
============

Experimentation with spy screen capture and transfer through Gmail gateway to the attacker

In this particular program the intention was to experiment with dynamic screen capture and 

subsequent attachment of the generated image to the mail and its transfer through GMail SMTP 

gateway programmatically by means of GMail's mail.jar

The program is going to be further enhanced to capture the voice and video at arbitrary times, 

ideally on reception of a particular command from attacker.

One significant drawback of GMail's JAR library was the fact that the generated attachment is not able to

pass through some well-known mail gateways, including Hotmail (attachment is lost). Alternatively some 

services work without any problem e.g. russian Mail.ru

One possible explanation:

This is probably related to some extra information (e.g. about file being attached by user himself 

or by some third-party software) included into the generated mail at Gmail's SMTP gateway. This information 

is further checked at recipient SMTP gateway and in case if sender is software rather than the human then the

attachment is removed. Some mail gateways including Hotmail, do perform this (actually quite important from

security point of view) check while others like Mail.ru do not. 






