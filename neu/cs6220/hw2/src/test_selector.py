import unittest

from selector import information_gain
from selector import info
from selector import infoA


class TestSelectorClass(unittest.TestCase):
    def test_info(self):
        y = 'a'
        n = 'b'
        cls = [n,n,y,y,y,n,y,n,y,y,y,y,y,n]
        infoD = info(cls)
        self.assertEqual(round(infoD, 3), 0.940)

    def test_infoA(self):
        y = 'young'
        m = 'middle_age'
        s = 'senior'
        yes = 'a'
        no = 'b'
        age = [y,y,m,s,s,s,m,y,y,s,y,m,m,s]
        cls = [no,no,yes,yes,yes,no,yes,no,yes,yes,yes,yes,yes,no]
        infoDA = infoA(age, cls)
        self.assertEqual(round(infoDA, 3), 0.694)

    def test_information_gain(self):
        data = [
            ['youth',          'high',   'no',  'fair'],
            ['youth',          'high',   'no',  'excellent'],
            ['middle aged',    'high',   'no',  'fair'],
            ['senior',         'medium', 'no',  'fair'],
            ['senior',         'low',    'yes', 'fair'],
            ['senior',         'low',    'yes', 'excellent'],
            ['middle aged',    'low',    'yes', 'excellent'], 
            ['youth',          'medium', 'no',  'fair'],
            ['youth',          'low',    'yes', 'fair'],
            ['senior',         'medium', 'yes', 'fair'], 
            ['youth',          'medium', 'yes', 'excellent'],
            ['middle aged',    'medium', 'no',  'excellent'],
            ['middle aged',    'high',   'yes', 'fair'],
            ['senior',         'medium', 'no',  'excellent']
        ]
        names = ['age', 'income', 'student', 'credit']
        yes = 'a'
        no = 'b'
        cls = [no,no,yes,yes,yes,no,yes,no,yes,yes,yes,yes,yes,no]

        attr_i = information_gain(data, names, cls, [0,1,2,3])
        self.assertEqual(attr_i[0], 0)
        print attr_i[1]

if __name__ == '__main__':
    unittest.main()
