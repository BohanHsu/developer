import unittest
from fake_random import FakeRandom

class TestFakeRandomClass(unittest.TestCase):
    def setUp(self):
        #self.frandom = FakeRandom(range(0,10))
        FakeRandom.set_sequence(range(0,10))

    def test_random(self):
        self.assertEqual(FakeRandom.random(), 0)
        self.assertEqual(FakeRandom.random(), 1)
        self.assertEqual(FakeRandom.random(), 2)
        self.assertEqual(FakeRandom.random(), 3)

if __name__ == "__main__":
    unittest.main()
