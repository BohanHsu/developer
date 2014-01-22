def scope_test():
	def do_local():
		spam = "local spam"
	def do_nonlocal():
		spam = "nonlocal spam"
	def do_global():
		spam = "global spam"
	spam = 'test spam'
	do_local()
	print('after local assignment:', spam)
	do_nonlocal
	print('after nonlocal assignment:', spam)
	do_global()
	print('after global assignment:', spam)

scope_test()
print('In golba scope:', spam)
