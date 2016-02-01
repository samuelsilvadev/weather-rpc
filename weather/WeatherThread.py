#!/usr/bin/python
#-*- coding: iso-8859-15 -*-

import thread
import time
import datetime

from forecastiopy import *
import WeatherDb

apikey = "0f1152b5fbe3929684513523890f5783"
Quixada = [-4.9716, -39.0161]
sleep = 300

def getResponse(delay):
    print "Iniciando Captura de Dados => " + timestamp()
    saveFile("Iniciando Captura de Dados => " + timestamp())
    count = True
    while count == True:
        fio = ForecastIO.ForecastIO(apikey, units=ForecastIO.ForecastIO.UNITS_AUTO, lang=ForecastIO.ForecastIO.LANG_PORTUGUESE, latitude=Quixada[0], longitude=Quixada[1])
        time.sleep(delay)
        if fio.has_currently() is True:
            currently = FIOCurrently.FIOCurrently(fio)
            print "Captura => " + timestamp()
            saveFile("Captura => " + timestamp())

            t = datetime.datetime.fromtimestamp(currently.time).strftime('%Y-%m-%d %H:%M:%S')
            summary = currently.summary
            icon = currently.icon
            precipIntensity = currently.precipIntensity
            precipProbability = currently.precipProbability
            precipType = currently.precipType
            temperature = currently.temperature
            apparentTemperature = currently.apparentTemperature
            humidity = currently.humidity
            windSpeed = currently.windSpeed
            cloudCover = currently.cloudCover
            pressure = currently.pressure
            ozone = currently.ozone

            query =  "INSERT INTO currently (createdat, summary, icon, precip_intensity, precip_probability, precip_type, temperature, apparent_temperature, humidity, wind_speed, cloud_cover, pressure, ozone) VALUES (%s, %s, %s, %f, %f, %s, %f, %f, %f, %f, %f, %f, %f)" %('"'+t+'"','"'+summary+'"','"'+icon+'"',precipIntensity,precipProbability,'"'+precipType+'"', temperature, apparentTemperature, humidity,windSpeed,cloudCover,pressure ,ozone)
            print query
            print "\n"
            WeatherDb.execQuery(query)
        else:
            print 'Não possui Informações'
            saveFile('Não possui Informações')

def saveFile(content):
    arquivo = file("weather.log", 'a+')
    arquivo.write(content)
    arquivo.write("\n")
    arquivo.close()

def timestamp():
    ts = time.time()
    st = datetime.datetime.fromtimestamp(ts).strftime('%Y-%m-%d %H:%M:%S')
    return st

def start():
    try:
       thread.start_new_thread( getResponse, (sleep, ) )
    except:
       print "Error: unable to start thread"
    while 1:
       pass

if __name__ == '__main__':
    start()
