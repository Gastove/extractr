
Overview
==========

Waaaaugh. Ok. So much things. 

So here's the deal, I'm going to try and do this with the following tools:
1. Scala 2.10.1 (using the simple build tool, or sbt)
2. Scalding -- github.com/twitter/scalding -- Map/Reduce abstraction framework for Scala
3. Mahout -- http://mahout.apache.org -- Machine Learning Framework
4. Hadoop -- http://hadoop.apache.org -- Big Fucking Computation Engine, mostly obfuscated by other tools. *Might* be the weak link in this plan.
5. Scalatra -- www.scalatra.org -- Web Framework for Scala, handles routing, http requests, etc. In short: allows this all to be an API when it grows up.
6. Heroku -- get.heroku.com -- Actual web hosting; integrates with git, SBT, Scalatra. And? Heroku itself runs on Amazon EC2 instances -- which means I could run a Hadoop server in EC2 and potentially get a low-latency connection between it and Heroku (which can totally handle the external server access, which is Six Flavors of Rad). 
7. Amazon Elastic Compute Cloud -- http://aws.amazon.com/ec2/ -- The free tier... might do it for a while. It's tiny and adorable, and with any luck, totally good for a proof of concept. The free tier gives 750 hours/month on Micro instances, which is actually just about a month of constant Micro EC2? Huh. Neat!

Current Goals
==========

1. Build a core NLP/ML engine that can extract everything that's a "Recipe" out of an HTML webpage scrape.
2. Serve it on the web as an API such that I can take an incoming search term (i.e. "roasted chicken") and return a page of recipes for roasted chicken + source URL
3. Assuming 1+2 work, extend core NLP/ML engine "process" recipes into a condensed, "mean" form. 


Tool Choice Notes
==========
Scala ML/NLP Libs:
-- Breeze: Pretty cool project, but painfully under-documented. Likely to spend all my time beating my face 
-- Mahout: This one seems like the way to go, honestly. *Far* better support/documentation, hillariously scalable. (No fucking seriously. Requires Hadoop for some processes, but on the upside, *can* use Hadoop for all processes, meaning we could deploy to EC2. Speed? Not a problem here.)

Here's what I know so far:

Breeze: probably far lighter weight, but also undergoing a *huge* transition right now, involving serious refactoring and a lot of "extract this piece and make it a totally separate project." Breeze will probably be pretty rad... next year. Keep an eye on it, especially if my other idea goes south.

Mahout + Scalding --
There are reasons this is seriously cool, and some reasons this is problematic. Lets review:

What it is/how it hooks up:
Mahout is a machine learning and analysis library that sits on top of Hadoop. Scalding is a scala package that lets you write Hadoop map/reduce jobs in a pleasingly scala-esque way (by handling a lot of low-level data format manupulation, which is a *chore*). So Scalding becomes the context in which a Mahout job is written, and that job is run on Hadoop. The final product can be packaged into a web API with Scalatra, which integrates with Heroku. Fuckin' A. This... is gonna be ok.

The Good
==========
1) This is all done in Scala, and comes pre-equipped with all the advantages of such: type safety, fast as you like, elegant, functional, and runs on a JVM (meaning it can run nearly anywhere). 
2) Mahout is fucking *robust*. This is industrial-grade Machine Learning at its more-or-less finest. We don't have to worry about rolling our own algorithms; we don't have to worry about whether or not they'll produce rigorous results. 
3) Similarly, Hadoop is about as scalable as it gets. Whether or not this matters is a very good question, and is mostly a question of implementation (i.e., do we want to run some sort of huge, powerful, web-crawly info-digesty database of recipes, or generate everthing to-order?) But: Hadoop runs real well on Amazon EC2 instances, whether we buy one machine or twenty thousand, and that could be advantageous too.
   3a) BUT, Ross needs to learn some Hadoop anyways, so running with Hadoop/Mahout/Scalding serves him pretty well at the moment.

The Bad
==========
1) Well here's an obvious one: wtf *Hadoop*? This is like, "I need to start a fire in this fire place here I've got this PLASMA CANNON" levels of overkill.
2) ...you know, I think point 1 about sums it up. There are frustratingly good odds that the better answer here is, "load a freaking NLP library in Python and just get this done".
3) Okay, fine: I have some concerns about this all building into a real API that can legitimately be used to this purpose. Hooray? Hooray.
4)...and the more I think about it, running Hadoop on EC2 instances might be... fun? And/or expensive? Look the fuck in to this Ross no seriously? I mean, OK: there is a free tier... hrm.
