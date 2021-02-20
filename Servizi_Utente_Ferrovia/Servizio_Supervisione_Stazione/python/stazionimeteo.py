#!/usr/bin/python

import json
import datetime, time
from sys import stdout

import urllib.request
import urllib.parse


regione = 0
meteoregioni = {}
onetoten = range(0,30)
for count in onetoten:
	try:
		url = 'http://www.viaggiatreno.it/viaggiatrenonew/resteasy/viaggiatreno/datimeteo/'+str(regione)
		f = urllib.request.urlopen(url)
		js = f.read().decode('utf-8')
		d = json.loads(js)
		regione+=1
		meteoregioni.update( d)
	except:
		#print(count)
		regione+=1
	
#print(js)

codStazione = {}
#d = json.dumps(meteoregioni)
for key in meteoregioni:
	ele = meteoregioni[key]['codStazione']
	codStazione[ele] = 0 
	
#print(codStazione)

#exit(0)

with open('../src/main/resources/stazioni_coord.geojson') as json_file:
    data = json.load(json_file)
    features = data['features']
    newfeatures = []
    for element in features:
    	id_staz = element['properties']['id_staz']
    	if id_staz in codStazione:
    		newfeatures.append(element)
    ff = {"type": "FeatureCollection"}
    ff["features"] = newfeatures
    print( ff)