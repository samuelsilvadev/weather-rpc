#!/usr/bin/python
#-*- coding: iso-8859-15 -*-

import SocketServer
from SimpleXMLRPCServer import SimpleXMLRPCServer,SimpleXMLRPCRequestHandler
import WeatherDb
import os
import json

class AsyncXMLRPCServer(SocketServer.ThreadingMixIn,SimpleXMLRPCServer): pass
print "Iniciando XML RPC Server"
print "Aguardando Requisições"
#print os.environ['OPENSHIFT_PYTHON_IP']
#print os.environ['OPENSHIFT_PYTHON_PORT']

# Example class to be published
class WeatherService:
    def currently(self):
        currently = WeatherDb.select("SELECT * FROM currently WHERE id = (SELECT MAX(id) FROM currently)")
        for row in currently:
            idcurrently = row[0]
            createdat = row[1]
            summary = row[2]
            icon = row[3]
            precipIntensity = row[4]
            precipProbability = row[5]
            precipType = row[6]
            temperature = row[7]
            apparentTemperature = row[8]
            humidity = row[9]
            windSpeed = row[10]
            cloudCover = row[11]
            pressure = row[12]
            ozone = row[13]
            #
            weather = json.dumps({'id':idcurrently,'createdat':createdat,'summary':summary,'icon':icon,'precip_intensity':precipIntensity,'precip_probability':precipProbability,'precip_type':precipType,'temperature':temperature,'apparent_temperature':apparentTemperature,'humidity':humidity,'wind_speed':windSpeed,'cloud_cover':cloudCover,'pressure':pressure,'ozone':ozone})
        return weather

server = AsyncXMLRPCServer(('10.0.1.124', 8000), SimpleXMLRPCRequestHandler)

server.register_instance(WeatherService())

server.serve_forever()
