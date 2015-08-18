MyFantasyLeague.com Data Collector
===

NOTE: This is currently implemented and tested to work with Phantom js on Java 8.

This is a work in progress screen scraping utility to pull in league data from fantasy football leagues and analysis sites.

This project is intended to be the collection process that pulls data from multiple sources into a single instance of mongodb.

The current plan is to build a MEAN application to present this data in a valuable manner.


Implementation Details
===

* PhantomJS is used to aid in preliminary screen scraping on myfantasyleague.com
* Spring boot is used to bootstrap and handle dependency injection and task scheduling
* A lot of the configuration is still hard-coded, I need to move this to an application.properties file.
* I was able to pull in some projection data via Http GET requests via a json artifact on fantasysharks.com