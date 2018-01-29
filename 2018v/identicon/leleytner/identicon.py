import random
colors = ['#F44336', '#9C27B0', '#3F51B5', '#2196F3', '#00BCD4', '#FFC107', '#FFEB3B']

def md5(string, salt='lollol'):
    import hashlib
    return hashlib.md5((string+salt).encode()).hexdigest()

def check_activation():
    # check probability of activation
    return random.random() > 0.65

def generate(hashcode, size=(5,5)):
    random.seed(hashcode+'lol')
    template = """<rect x="{}" y="{}" height="20" width="20" fill="{}" /> """
    result = '<svg width="{}" height="{}">'.format(size[1]*20, size[0]*20)
    
    color_index = ord(hashcode[-1])%len(colors)
    unique_columns = size[1]//2 + size[1]%2
    for row in range(size[0]):
        for column in range(unique_columns):
            if check_activation():
                # add pixel
                result += template.format(column*20, row*20, colors[color_index])
                
                # add symmetrical pixel
                symmetrical_column = size[1] - 1 - column
                # if we draw central column we do not need to draw it twice
                if symmetrical_column == column:
                    continue
                result += template.format(symmetrical_column*20, row*20, colors[color_index])
                
    return result+'</svg>'

print(generate(md5(sys.args[0])), size=(5,5))
