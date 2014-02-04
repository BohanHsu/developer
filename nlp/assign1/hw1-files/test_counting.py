def counting(l):
	r = []
	if len(l) == 0:
		return r
	w = l[0]
	count = 1
	for i in range(1,len(l)):
		if cmp(l[i],w) == 0:
			count = count + 1
		else:
			r.append((w,count))
			w = l[i]
			count = 1
	
	r.append((w,count))	
	return r


r = counting([1,1,2,2,3,3,3,4,5])

print r
