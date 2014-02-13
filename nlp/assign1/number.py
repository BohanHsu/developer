import commands
import os
import sys


dict1 = {}
dict1[0] = ''
dict1[1] = 'one'
dict1[2] = 'two'
dict1[3] = 'three'
dict1[4] = 'four'
dict1[5] = 'five'
dict1[6] = 'six'
dict1[7] = 'seven'
dict1[8] = 'eight'
dict1[9] = 'nine'
dict1[10] = 'ten'
dict1[11] = 'eleven'
dict1[12] = 'twelve'
dict1[13] = 'thirteen'
dict1[14] = 'fourteen'
dict1[15] = 'fifteen'
dict1[16] = 'sixteen'
dict1[17] = 'seventeen'
dict1[18] = 'eighteen'
dict1[19] = 'nineteen'
dict2 = {}
dict2[0] = ''
dict2[2] = 'twenty'
dict2[3] = 'thirty'
dict2[4] = 'forty'
dict2[5] = 'fifty'
dict2[6] = 'sixty'
dict2[7] = 'seventy'
dict2[8] = 'eighty'
dict2[9] = 'ninety'
comma = {}
comma[1] = 'thousand'
comma[2] = 'million'

#
#
#
#
def single(num):
	num

# int, int -> list of string
# h is the higher digit
# l is the lower digit
#
def teen(h,l):
	list = []
	if h == 1:
		s = dict1[(h*10+l)]
		list.append(s)
	else:
		s = dict2[h]
		list.append(s)
		s = dict1[l]
		list.append(s)
    
	return list

# list of int -> list of string
# which the length of the list at most three
# return a list of string
#
def thr(num):
	num.reverse()
	list = []
	l = len(num)
	if l == 1:
		if num[0] != 0:
			s = dict1[num[0]]
			list.append(s)
    
	elif l == 2:
		list = teen(num[1],num[0])
    
	elif l == 3:
		if num[2] != 0:
			s = dict1[num[2]]
			list.append(s)
			list.append('hundred')
			list = list + teen(num[1],num[0])
		else:
			list = list + teen(num[1],num[0])
    
	return list


# list of int -> list of string
# where the lower the index the lower the digit
# return the string
#
def number(num):
	if num == 0:
		return ['zero']
	list = thr(num[-3:])
	num = num[:-3]
	count = 1
	while len(num) > 0:
		list = thr(num[-3:]) + [comma[count]] + list
		num = num[:-3]
		count = count + 1
	
	nl = []
	for l in list:
		if cmp(l,'') != 0:
			nl.append(l)
	
	return nl


# int -> String
# take a integer
# return this integer as the input of fst
#
def formating(n):
	list = []
	while n != 0:
		list = [n%10] + list
		n = n/10
	
	s = ''
	l = len(list)
	for i in range(0,l):
		s = s + str(i) + '\t' + str((i+1)) + '\t' + str(list[i]) + '\t' + str(list[i]) + '\n'
	
	s = s + str(l) + '\n'
#	print s
	return s


# string -> list of string
# calling the command on shell and return the result in a list
#
def shell(cmd):
	t = commands.getstatusoutput(cmd)
	t = t[1]
	l = t.split('\n')
	return l

#cmd = 'fstcompose t980605043.fst fst.fst | fstproject --project_output | fstrmepsilon | fstprint --isymbols=mysyms.syms --osymbols=mysyms.syms'
#l = shell(cmd)

def pronounce(l):
	list = []
	for line in l:
		ws = line.split('\t')
		if len(ws) >= 3:
			list.append(ws[2])

	return list

#ws = pronounce(l)
#print ws

def buildingfst(n, filename):
	s = formating(n)
	f = open(filename,'w')
	f.writelines([s])
	f.close()

#buildingfst(789456123,'./t789456123.txt')

def filenamegen(n):
	rand = 'dmwfwe'
	s = str(n)
	return './' + rand + s

def batch(nt,nf,fst,lib):
	cmdcompile = 'fstcompile --isymbols='+lib+' --osymbols='+lib+' < '+nt+' > '+nf
	cmdcompose = 'fstcompose '+nf+' '+ fst + ' | fstproject --project_output | fstrmepsilon | fstprint --isymbols='+lib+' --osymbols=' + lib
	shell(cmdcompile)
	s = shell(cmdcompose)
#	print s
	p = pronounce(s)
	os.remove(nt)
	os.remove(nf)
	return p

def inttolist(n):
	l = []
	while n > 0:
		l = [n%10] + l
		n = n/10

	return l

def test(n,fst,lib):
	namet = filenamegen(n) + '.txt'
	namef = filenamegen(n) + '.fst'
	buildingfst(n,namet)
	p = batch(namet,namef,fst,lib)
#	print p
	r = number(inttolist(n))
#	print r
	if len(p) == 0:
		return False

	for i in range(0,min(len(p),len(r))):
		if cmp(p[i],r[i]) != 0:
			return False
	
	return True


def testfst(fst,lib,sta,end):
	print 'start'
	wro = []
	for i in range(sta,end):
		print 'testing: ',i
		res = test(i,fst,lib)
		if res == True:
			#print '\t',i,'is right!'
			pass
		else:
			#print '\t', i, 'is worong:', res
			wro.append(i)
	print wro
	print 'end'


# the path of FST file
fst = sys.argv[1]
# the path of FST symbol table file's path
lib = sys.argv[2]
# start test from number
sta = int(sys.argv[3])
# end test before number
end = int(sys.argv[4])

testfst(fst,lib,sta,end)
#print inttolist(1234)
#print number([1,0,0])
#print test(1000,'fst.fst','mysyms.syms')
