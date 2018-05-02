'''
Name: Ziqiang LI
StudentNo: 11510352
Creation data:
'''

import matplotlib.pyplot as plt
import re
import colorsys as cs


def get_rgb_color(ratio, bright):
    if ratio == -1 and bright == -1:
        return 'w'
    h = ratio / 3
    s = 1
    v = bright*4 / 5 + 1/5
    return cs.hsv_to_rgb(h, s, v)


def format_color(color, length):
    print(color)
    out = [['w', 'w'] for i in range(length)]
    for i in range(len(color)):
        out[color[i][0] - 1][1] = color[i][1]
    return out


pattern = re.compile(r'\[java\] line (\d+): ratio: (-?\d+\.\d*), brightness: (-?\d+\.\d*)')

color = []
with open('data.txt', 'r') as f:
    for line in f.readlines():
        line = line.strip()
        data = pattern.findall(line)
        if data:
            line_no, ratio, bright = pattern.findall(line)[0]
            color.append((int(line_no), get_rgb_color(float(ratio), float(bright))))

code = []
count = 1
with open('Triangle.java', 'r') as tri_f:
    for line in tri_f.readlines():
        code.append([count, line.strip('\n')])
        count += 1
color = format_color(color, len(code))
print(color)

fig, ax = plt.subplots()
# hide axes
fig.patch.set_visible(False)
ax.axis('off')
ax.axis('tight')

tb1 = ax.table(cellText=code[:], cellColours=color[:], cellLoc='left', loc='center')
tb1.auto_set_column_width(0)
tb1.scale(0.9, 0.8)

plt.savefig("fault.png", dpi=1000)
plt.show()
