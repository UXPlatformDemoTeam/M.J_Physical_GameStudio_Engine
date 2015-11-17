#pragma once
#include <vector>

class SinglePath;
class Path
{
private :
	std::vector<SinglePath> mPath;
public:
	Path(char * map_data);
	SinglePath* getPath(int index);
	~Path();
};

class SinglePath {
private:
	int start_x;
	int start_y;
	int dir[100];
	int len[100];
	int size;

public:
	SinglePath(char* line_map_data);
	int getStartX() { return this->start_x; }
	int getStartY() { return this->start_y; }
	int* getDirections() { return this->dir; }
	int* getLens() { return this->len; }
	int getSize() { return this->size; }
};
