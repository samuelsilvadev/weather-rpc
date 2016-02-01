#!/usr/bin/python
 #-*- coding: iso-8859-15 -*-

import MySQLdb
import os

#aServidor = os.environ['OPENSHIFT_MYSQL_DB_HOST']
aServidor = '127.0.0.1'
#aUsuario  = os.environ['OPENSHIFT_MYSQL_DB_USERNAME']
aUsuario  = 'root'
#aSenha    = os.environ['OPENSHIFT_MYSQL_DB_PASSWORD']
aSenha    = 'root'
aBanco    = "weather"

db = MySQLdb.connect(aServidor, aUsuario, aSenha, aBanco)
cursor = db.cursor()

def execQuery(query):
    cursor.execute(query)
    db.commit()

def select(query):
  try:
    cursor.execute(query)
    results = cursor.fetchall()
    return results
  except:
    print("Erro: Não foi possível buscar os dados")
    return 0
