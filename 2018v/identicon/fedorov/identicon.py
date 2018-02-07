import hashlib
import numpy as np
import svgwrite
import sys

def hash_md5(line,verbose = 1):
    h = hashlib.md5(line.encode())
    if verbose == 1:
        print("Hashed.")
    return h.hexdigest()


def choose_color(h, verbose = 1):
    colors = ("(255,0,0)","(0,255,0)","(0,0,255)","(255,255,0)","(0,255,255)","(255,0,255)","(128,128,128)")
    r_color = "rgb"+colors[sum([ord(i) for i in h]) % len(colors)]
    c_color = "rgb"+colors[int(np.mean([ord(i) for i in h])*100/13) % len(colors)]
    if verbose == 1:
        print("Colors choosed.")
    return c_color, r_color

def make_matrix(h, verbose = 1):
    weight_r = [str(i) for i in range(1,10)]
    weight_c = ['a','b','e','i','o','u']
    size = (360,360)
    #rects
    bh = []
    for i in h:
        if i in weight_r:
            bh.append(True)
        else:
            bh.append(False)
    l = 8
    matrix_r = [[[] for j in range(l)] for i in range(l)]
    f = 0
    for i in range(l):
        for j in range(l//2):
            pos1 = (j*size[1]//l,i*size[0]//l)
            pos2 = ((l-1-j)*size[1]//l,i*size[0]//l)
            matrix_r[i][j] = (bh[f], pos1)
            matrix_r[i][l-1-j] = (bh[f], pos2)
            f += 1
#     circles 
    bh = []
    for i in h:
        if i in weight_c:
            bh.append(True)
        else:
            bh.append(False)
    matrix_c = [[[] for j in range(l)] for i in range(l)]
    f = 0
    for i in range(l):
        for j in range(l//2):
            pos1 = (j*size[1]//l,i*size[0]//l)
            pos2 = ((l-1-j)*size[1]//l,i*size[0]//l)
            matrix_c[i][j] = (bh[f], pos1)
            matrix_c[i][l-1-j] = (bh[f], pos2)
            f += 1
    if verbose == 1:
        print("Data Parsed.")
    return matrix_r, matrix_c ,size
        
def make_svg(r_data, c_data, c_color, r_color, file, size, verbose = 1):
    dwg = svgwrite.Drawing(filename = file,size = (str(size[0])+"px", str(size[1])+"px"))
    dwg.add(dwg.rect(insert = (0,0), size = (str(size[0])+"px",str(size[1])+"px"), stroke = "black", fill="rgb(255,255,255)"))
    d = []
    for i in r_data:
        d += i
    for i in d:
        if i[0]:
            ob = i[1]
            dwg.add(dwg.rect(insert = ob,stroke = r_color, fill = r_color,size = ("45px", "45px")))
    d = []
    for i in c_data:
        d += i
    for i in d:
        if i[0]:
            ob = i[1]
            dwg.add(dwg.circle(center = (ob[0]+22.5,ob[1]+22.5),stroke = c_color,r = 20,fill = c_color))
    #dwg.save()
    if verbose == 1:
        print("Identicon generated.")
    return dwg.tostring()

def make_identicon(line,filename = "identicon.svg",verbose = 1):
    h = hash_md5(line,verbose)
    c_color, r_color = choose_color(h,verbose)
    r_data,c_data, size = make_matrix(h,verbose)
    ans = make_svg(r_data,c_data, c_color,r_color,filename,size,verbose)
    return ans
line = sys.argv[0]
#filename = input("Input filename, or leave it empty: ")
sv = make_identicon(line, filename='identicon.svg')

print(sv)
