# After update to 0.2.5 I keep getting DB exception while trying to edit a task #
You need to update the mongodb to 2.2.0 or higher. If you are using the runtime, than 0.2.1 or higher (not relevant for 64 bit Linux runtime).

# After update the frontend shows some strange errors #
Try to clear the browsing data. E.g. in Chrome it is: Tools -> Clear Browsing Data ->, than check
  * Empty the cache
  * Delete cookies and other site and plug-in data
  * Clear data from hosted apps
Than press Clear Browsing Data

# The frontend is REALLY slow for more nested workflow #
You have two options:
  * Use Google Chrome, it is nearly instantaneous in it
  * In the Edit Board dialog uncheck the "Balance vertical items' sizes"