import random
import os
import subprocess
import argparse

 
if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('--name', type=str)
    args = parser.parse_args()
    assert args.name is not None, "You have to specify an optional agrument --name"
    random.seed(args.name)

    listdir = os.listdir('./')
    listdir.remove('run.py') # ignore this file
    folder_name = random.choice(listdir)

    print('Identicon from ' + folder_name)
    if os.path.isfile(folder_name+'/compile.sh'):
        subprocess.call(folder_name+'/compile.sh')
    subprocess.check_call([folder_name +"/run.sh", args.name])