import random
import sys

N = 5
M = 20

def rect(x, y, width, height, col_r, col_g, col_b):
    print('<rect x="' + str(x) + '" y="' + str(y) + '" width="' + str(width) + '" height="' + str(height) + '" style="fill:rgb('+str(col_r)+','+str(col_g)+','+str(col_b)+')" />')

def circle(cx, cy, r, col_r, col_g, col_b):
    print('<circle cx="' + str(cx) + '" cy="' + str(cy) + '" r="' + str(r) + '" style="fill:rgb('+str(col_r)+','+str(col_g)+','+str(col_b)+')" />')

def identicon(s, f):
    im = [[0 for i in range(N)] for j in range(N)]
    random.seed(s)
    
    for i in range(N):
        for j in range((N + 1) // 2):
            im[i][j] = int(random.random() > 0.5)
            im[i][-j - 1] = im[i][j]
    
    print('<p>' + s + '</p>')
    print('<svg width="' + str(M*N) + '" height="' + str(M*N) + '">')
    
    r=255
    g=255
    b=255
    while r + b + g > 500:
        r = random.randint(0, 255)
        g = random.randint(0, 255)
        b = random.randint(0, 255)
    
    if f == 'r':
        for i in range(N):
            for j in range(N):
                if im[i][j]:
                    rect(M*j, M*i, M, M, r, g, b)
    
    elif f == 'c' or f == 'c++':
        for i in range(N):
            for j in range(N):
                if im[i][j]:
                    circle(M*j + M//2, M*i + M//2, M//2, r, g, b)
                    if i != 0 and im[i-1][j]:
                        rect(M*j, M*i - M//2, M, M, r, g, b)
                    if j != 0 and im[i][j-1]:
                        rect(M*j - M//2, M*i, M, M, r, g, b)
                if not im[i][j] and f == 'c++':
                    if i != 0 and j != 0 and im[i-1][j] and im[i-1][j-1] and im[i][j-1]:
                        rect(M*j - M//2, M*i - M//2, M, M, r, g, b)
                    if i != N-1 and j != 0 and im[i+1][j] and im[i+1][j-1] and im[i][j-1]:
                        rect(M*j - M//2, M*i + M//2, M, M, r, g, b)                    
                    if i != 0 and j != N-1 and im[i-1][j] and im[i-1][j+1] and im[i][j+1]:
                        rect(M*j + M//2, M*i - M//2, M, M, r, g, b)
                    if i != N-1 and j != N-1 and im[i+1][j] and im[i+1][j+1] and im[i][j+1]:
                        rect(M*j + M//2, M*i + M//2, M, M, r, g, b)
                    circle(M*j + M//2, M*i + M//2, M//2, 255, 255, 255)


print('<html>\n<body>\n')
identicon(sys.argv[1], 'c++')
print('\n</html>\n</body>\n')