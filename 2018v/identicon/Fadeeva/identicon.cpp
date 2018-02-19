#include <iostream>
#include <algorithm>
#include <cstdio>
#include <vector>
#include <cassert>
#include <fstream>

using namespace std;

const int INF = 1e9;

string s;

int f = INF;

int my_pow(int a, int b) {
	int res = 1;
	while (b != 0) {
		if (b % 2 == 1)
			res *= a;
		a *= a;
		b /= 2;
	}
	return res;
}

int get(int c) {
	int kek = 0;
	for (int i = 0; i < c; i++) {
		kek = kek * 10 + s[(int)s.length() - 1] - f;
		s.resize((int)s.length() - 1);
	}
	
	return kek % my_pow(10, c);
}

vector <string> colors = {"green", "blue", "yellow", "black", "white", "red", "orange", "pink", "grey", "purple"};

int counter = 0;

int main() {
	srand(time(NULL));
	
	cin >> s;
	
	for (int i = 0; i < (int)s.length(); i++)
		f = min(f, (int)s[i]);
	
	cout << "<html>" << endl;
	cout << "<body>" << endl;
	cout << "<svg width=\"300\" height=\"300\">" << endl;
	
	while (s.length() >= 5 && counter < 10000) {
		int x0 = get(2);
		int y0 = get(2);
		int r = get(1) + 3;
		int w = 0;
		int c1 = 0;
		int c2 = counter % (int)colors.size();
		
		cout << "<circle cx=\"";
		cout << x0;
		cout << "\" cy=\"";
		cout << y0;
		cout << "\" r=\"";
		cout << r;
		cout << "\" stroke=\"";
		cout << colors[c1];
		cout << "\" stroke-width=\"";
		cout << w;
		cout << "\" fill=\"";
		cout << colors[c2];
		cout << "\" />";
		cout << endl;
		
		counter++;
	}
	
	cout << "</svg>" << endl;
	cout << "</body>" << endl;
	cout << "</html>" << endl;
	
	return 0;
}
