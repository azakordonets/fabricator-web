[![Build Status](https://travis-ci.org/azakordonets/fabricator-web.svg?branch=master)](https://travis-ci.org/azakordonets/fabricator-web) &nbsp;&nbsp;&nbsp;&nbsp;   [![Coverage Status](https://coveralls.io/repos/azakordonets/fabricator-web/badge.png)](https://coveralls.io/r/azakordonets/fabricator-web)


This project is an extension to [Fabricator library](https://github.com/azakordonets/fabricator) that allows to get generated fake data via API calls 
web interface. Demo of the application can be found [here](fabricator-web.herokuapp.com) . 

API allows you to get fake test date with REST API calls. Below you can find a description for every available call. Please note that data can be 
received in two formats : json and plain text. By default json format is returned. To get plain text response just specify ```json=false``` in the call.

## How to install 

First you need to install [Typesafe Activator](https://typesafe.com/get-started) . After that 

```bash 

git clone https://github.com/azakordonets/fabricator-web.git

cd fabricator-web

activator run 
```

## Usage

Just as fabricator library, available data can be divided into set of groups : 

* Alphanumeric - generates random numbers and strings:
* Calendar - generates random time and date:
* Contact - generates contact and person details information :
* Finance - generates random credit cards, bsn numbers
* Internet - generates random url's, domains, e-mails, ip's, mac addresses, color codes, social networks id's
* Location - generates random coordinates, geohash
* Mobile - generates random mobile platforms push tokens or id's
* Words - generates random words, sentences and even blocks of text

Alphanumeric
------------
This module allows you to generate any random number or string. As for strings, you can generate either fully random string,
or you can generate string basing on a pattern.

```scala
http://localhost:9000/api/v1/alpha/integer // {"integer":524}

http://localhost:9000/api/v1/alpha/integer?json=false // 147

http://localhost:9000/api/v1/alpha/integer?min=100 // {"integer":10284}

http://localhost:9000/api/v1/alpha/integer?min=100&max=200 // {"integer":136}

http://localhost:9000/api/v1/alpha/integer?min=100&max=200&amont=5 // {"integers":[12,17,18,16,12]}

http://localhost:9000/api/v1/alpha/integer?min=100&max=200&amont=5 // {"doubles":[14.43910694944854,18.525493884332757,16.28766388648211,14.07562943922775,11.612076015731308]}

http://localhost:9000/api/v1/alpha/double?min=100&max=200&amont=5&accuracy=2 // {"doubles":[15.95,17.45,19.14,10.68,19.96]}

http://localhost:9000/api/v1/alpha/double?min=100&max=200&amont=5&accuracy=2 // {"doubles":[15.95,17.45,19.14,10.68,19.96]}

http://localhost:9000/api/v1/alpha/hash?length=10&amount=2 // {"hashes":["873dbc8a6b","37d04a3574"]}

http://localhost:9000/api/v1/alpha/guid?length=10&amount=2 // {"guids":["fb3b2d2b-a6a1-10f92-85bb-88ef3fcf1281","ad3d91f6-1555-10f6c-8dba-8f73d7adf347"]}

localhost:9000/api/v1/alpha/letterify?pattern=123??? // {"value":"123NvF"}

http://localhost:9000/api/v1/alpha/numerify?pattern=ABC%23%23%23 // {"value":"ABC274"}

http://localhost:9000/api/v1/alpha/botify?pattern=ABC%23%23%23??? // {"value":"ABC274AdF"}
```

Besides random values, it is also possible to generate ranges of values with specifying minimum, maximum and 
step values. Step is a mandatory parameter.  

```scala
http://localhost:9000/api/v1/alpha/range/integer?min=10&max=15&step=1 // {"integers":[10,11,12,13,14,15]}
 
http://localhost:9000/api/v1/alpha/range/double?min=10&max=15&step=1.5 // {"doubles":[10.0,11.5,13.0,14.5]}

http://localhost:9000/api/v1/alpha/range/string?min=10&max=15&step=2&amount=2 // {"strings":["lWAO8-UCxH1rDY","k49UPrL4gI8w"]}

http://localhost:9000/api/v1/alpha/range/hash?min=10&max=15&amount=2 // {"hashes":["e50b314840e1c7","a349808975d83e"]}

http://localhost:9000/api/v1/alpha/range/letterify?pattern=123???&amount=3 // {"strings":["123buO","123ysR","123dNi"]}

http://localhost:9000/api/v1/alpha/range/botify?pattern=%23abc1?&amount=2 // {"strings":["5abc13","2abc15"]}
```

Calendar
--------

```scala
http://localhost:9000/api/v1/calendar/time // {"time":"21:11"}

http://localhost:9000/api/v1/calendar/time?twentyFourHours=false // {"time":"09:11"}

http://localhost:9000/api/v1/calendar/date // {"date":"18-02-2005"}

http://localhost:9000/api/v1/calendar/date?day=15 // {"date":"15-07-1993"}

http://localhost:9000/api/v1/calendar/date?day=15&month=2 // {"date":"15-02-1972"}

http://localhost:9000/api/v1/calendar/date?day=15&month=2&year=2013&format=dd/MM/yyyy%20HH:mm // {"date":"15/02/2013 07:23"}

http://localhost:9000/api/v1/calendar/dates?day=15&month=2&year=2013&format=dd/MM/yyyy%20HH:mm&amount=5 // {"dates":["15/02/2013 08:03","15/02/2013 04:23","15/02/2013 10:29","15/02/2013 02:15","15/02/2013 11:53"]}

http://localhost:9000/api/v1/calendar/datesRange?config={%22start%22:{%22year%22:2001,%22month%22:1,%22day%22:1,%22hour%22:0,%22minute%22:0},%22end%22:{%22year%22:2010,%22month%22:1,%22day%22:1,%22hour%22:0,%22minute%22:0},%22step%22:{%22year%22:1,%22month%22:1,%22day%22:1,%22hour%22:0,%22minute%22:0},%22format%22:%22dd-MM-yyyy%20hh:mm%22}&json=true // {"range":["01-01-2001 12:00","02-02-2002 12:00","03-03-2003 12:00","04-04-2004 12:00","05-05-2005 12:00","06-06-2006 12:00","07-07-2007 12:00","08-08-2008 12:00","09-09-2009 12:00","10-10-2010 12:00"]}

http://localhost:9000/api/v1/calendar/relativeDate?days=-5&month=+2&years=-10 // {"relative_date":"09-03-2005"}

http://localhost:9000/api/v1/calendar/relativeDate?days=-5&month=+2&years=-10&startPoint=10-10-2010 // {"relative_date":"05-10-2000"}
```

Contact
--------

```scala
http://localhost:9000/api/v1/contact/name // {"name":{"first_name":"Darius","last_name":"Predovic","full_name":"Isac Mertz"}}

http://localhost:9000/api/v1/contact/birthday // {"birthday":"14-03-1990"}

http://localhost:9000/api/v1/contact/birthday?age=34&format=dd/MM/yyyy // {"birthday":"14/03/1981"}
 
http://localhost:9000/api/v1/contact/address // {"address_details":{"house_number":"534","phone_number":"1-920-653-1902 x363","state":"Colorado","state_short_code":"SD","postcode":"42932-2576","street_name":"Heights","company":"LLC","address":"Point 59752, Apt. 904","appartment_number":"Suite 309"}}
 
http://localhost:9000/api/v1/contact/person // {"name":{"first_name":"Maryjane","last_name":"Windler","full_name":"Jerrod Farrell"},"address":{"house_number":"8401","phone_number":"(022)608-0224","state":"New Hampshire","state_short_code":"KY","postcode":"94006-4598","street_name":"Meadow","company":"LLC","address":"Loop 919, Apt. 228","appartment_number":"Suite 412"},"birthday":"14-03-1990","email":"jeromy_ryan171@gmail.com","bsn":"457227540","religion":"Confucianism","zodiac":"Pisces","height":"1.5757365990787684 cm","weight":"57 kg","blood_type":"A-","occupation":"Student Admissions Administrator"}
```

Finance
--------

```scala

http://localhost:9000/api/v1/finance/creditcard // {"card_number":"5203093841333903"}

http://localhost:9000/api/v1/finance/creditcard?cardType=visa // {"card_number":"4929239497055621"}

http://localhost:9000/api/v1/finance/creditcards?amount=2&cardType=discover // {"cards":["6011943309775595","6011003277887228"]}

```

Internet
--------

```scala
http://localhost:9000/api/v1/internet/appleToken // {"token":"tz4nurif99egypfjnhsruzktrlvn8exyyb9bsoagsx2ma0gwo6jnjqmvtxdckwto"}

http://localhost:9000/api/v1/internet/appleToken?amount=2 // {"tokens":["npv2tepteebkivov5juddecixoysg6yuou8ydnd6r7ggxih8vcwmq7kjcxxtocqu","0kfa0s69snmccibwynxolbxbafybv6hlxukxpkgq4fqe7smjo5kkyww4iqyvxpu2"]}

http://localhost:9000/api/v1/internet/url // {"url":"http://test.com/get?="}

http://localhost:9000/api/v1/internet/url?protocol=https&host=somehost.org&callName=iwanttogetthisuser // {"url":"https://somehost.org/iwanttogetthisuser?="}

http://localhost:9000/api/v1/internet/ip // http://localhost:9000/api/v1/internet/ip

http://localhost:9000/api/v1/internet/ip?ipv6=true // {"ip":"057B:B1E8:C27b:a963:BcFd:fa25:EA0e:aBae"}

http://localhost:9000/api/v1/internet/macaddress // {"macaddress":"c3387894-93f9-4896-9f23-c701e1e58f67"}

http://localhost:9000/api/v1/internet/uuid // {"uuid":"7a122cd9-e4fd-4be3-91a6-880b2354470b"}

http://localhost:9000/api/v1/internet/color // {"color":"#8472ab"}

http://localhost:9000/api/v1/internet/color?format=rgb&greyscale=true // {"color":"rgb(44,44,44)"}

http://localhost:9000/api/v1/internet/twitter // {"twitter":"@CarmeloLang"}

http://localhost:9000/api/v1/internet/hashtag // {"hashtag":"#ifanylow"}

http://localhost:9000/api/v1/internet/googleanalytics // {"trackCode":"UA-73388-46"}

http://localhost:9000/api/v1/internet/facebookid // {"id":"1109371992942712"}
```

Location
--------

```scala

http://localhost:9000/api/v1/location/altitude // {"altitude":"5659.80749"}

http://localhost:9000/api/v1/location/altitude?max=2000 // {"altitude":"1279.69959"}

http://localhost:9000/api/v1/location/depth?min=200 // {"depth":"-198.62580"}

http://localhost:9000/api/v1/location/coordinates // {"coordinates":"-21.86630, 40.49458"}

http://localhost:9000/api/v1/location/coordinates?accuracy=2 // {"coordinates":"-71.90, 90.92"}

http://localhost:9000/api/v1/location/latitude?accuracy=2 // {"latitude":"-17.25"}

localhost:9000/api/v1/location/longitude?accuracy=2 // {"longitude":"-11.49"}

http://localhost:9000/api/v1/location/geohash // {"geohash":"q2h3yzh4v9te"}

```

Mobile
------

```scala 

http://localhost:9000/api/v1/mobile/android // {"token":"APA91EBnXoOpiQ0-TAZp29MM0DEZ6WQpzDWibBkvVtRCrvMBa8t-GV-xRi8DZ-ByaJUYtpWa4gY057cgs2H50hAPVtY00amvsmKCpy0wZ5F0UfaIDzMpvOz0e968WHwS1nHcaAwNI1XUozH1HyeMUN9GSIysTpQReYBpEsQstB8MIWbZHAKMkk8"}

http://localhost:9000/api/v1/mobile/applepush?amount=2 // {"tokens":["e21d6c19474eec912b3c95f34ee116178f21f73ab7e962f353b46fc52fd6a1d6","41582955ec3a93b6e42939d9e8e1d5523e5ddd2f2752c2ccbd4917d9bc1664a5"]}

http://localhost:9000/api/v1/mobile/wp7 // {"token":"A=DBF9BA4B636C5FF2B15FDBEB5B43A778&E=b2d&W=6"}

http://localhost:9000/api/v1/mobile/wp8 // {"token":"a045UGx6VzlwTzNyY01RZkxXVkJpN0hMQ0xMNjk1"}

http://localhost:9000/api/v1/mobile/blackberry // {"token":"45a99374"}

```

Words
-----

```scala
http://localhost:9000/api/v1/words/words // {"word":"blood"}

http://localhost:9000/api/v1/words/words?amount=5 // {"words":["first","with","write","form","of"]}

http://localhost:9000/api/v1/words/paragraph // {"paragraph":"here if here form narrow down but any long will so if here long back first but look here with narrow"}

http://localhost:9000/api/v1/words/paragraph?length=20 // {"paragraph":"long first if by any"}

http://localhost:9000/api/v1/words/sentence?wordsAmount=20 // {"sentence":"down so long long of here first by with back by run so sun down back long but write will. "}
```