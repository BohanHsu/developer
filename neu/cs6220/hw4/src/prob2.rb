def p1(lam, ua, ub)
  lam * (ua ** 3) * (1 - ua)
end

def p2(lam, ua, ub)
  (1 - lam) * (ub ** 3) * (1 - ub)
end

#def p3(lam, ua, ub)
#  p1(lam, ua, ub)
#end

#def p4(lam, ua, ub)
#  p2
#end

#p3 = p1
#
#p4 = p2


def p5(lam, ua, ub)
  lam * ua * ((1 - ua) ** 3)
end

def p6(lam, ua, ub)
  (1 - lam) * ub * ((1 - ub) ** 3)
end

#def p7(lam, ua, ub)
#  (lam * (ua ** 3) * (1 - ua)) / ()
#end

def p7(lam, ua, ub)
  #p "p1 #{p1(lam, ua, ub)}"
  #p "p1 + p2 #{(p1(lam, ua, ub) + p2(lam, ua, ub))}"
  p1(lam, ua, ub) / (p1(lam, ua, ub) + p2(lam, ua, ub))
end

def p8(lam, ua, ub)
  p2(lam, ua, ub) / (p1(lam, ua, ub) + p2(lam, ua, ub))
end

def p9(lam, ua, ub)
  p7(lam, ua, ub)
end

def p10(lam, ua, ub)
  p8(lam, ua, ub)
end

def p11(lam, ua, ub)
  p5(lam, ua, ub) / (p5(lam, ua, ub) + p6(lam, ua, ub))
end

def p12(lam, ua, ub)
  p6(lam, ua, ub) / (p5(lam, ua, ub) + p6(lam, ua, ub))
end

def new_lam(lam, ua, ub)
  (p7(lam, ua, ub) + p9(lam, ua, ub) + p11(lam, ua, ub)) / 3
end

def new_ua(lam, ua, ub)
  #p p7(lam, ua, ub)
  #p p8(lam, ua, ub)
  #p p9(lam, ua, ub)
  #p p10(lam, ua, ub)
  #p p11(lam, ua, ub)
  #p p12(lam, ua, ub)
  (3 * p7(lam, ua, ub) + 3 * p9(lam, ua, ub) + 1 * p11(lam, ua, ub)) / (4 * p7(lam, ua, ub) + 4 * p9(lam, ua, ub) + 4 * p11(lam, ua, ub))
end

def new_ub(lam, ua, ub)
  (3 * p8(lam, ua, ub) + 3 * p10(lam, ua, ub) + 1 * p12(lam, ua, ub)) / (4 * p8(lam, ua, ub) + 4 * p10(lam, ua, ub) + 4 * p12(lam, ua, ub))
end

def main()
  lam = 0.5
  ua = 0.6
  ub = 0.4

  p "#{lam}, #{ua}, #{ub}"

  3.times do
    lam = new_lam(lam, ua, ub)
    ua = new_ua(lam, ua, ub)
    ub = new_ub(lam, ua, ub)
    p "#{lam}, #{ua}, #{ub}"
  end
end

main if __FILE__
