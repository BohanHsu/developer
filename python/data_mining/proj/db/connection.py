from peewee import *
import datatime

myDB = MySQLDatebase(host='localhost',
    port=3306,
    user='root',
    db='data-mining')

class MySQLModel(Model):
  """A base model that will user our MySQL database"""
  class Meta:
    database = myDB

class Article:

