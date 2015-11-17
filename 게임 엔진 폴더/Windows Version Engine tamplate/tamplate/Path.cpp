#include "Path.h"
#include "Map_controller.h"

Path::Path(char * map_data)
{
	std::vector<std::string> arr = split(map_data, "\n");

	for (int i = 0; i < arr.size(); i++){
		if (arr[i].find("//") <= 3 || (trim(arr[i]).compare("\r") == 0))
			continue;

		char *cstr = new char[arr[i].size() + 1];
		strcpy(cstr, arr[i].c_str());
		// do stuff
		SinglePath temp(cstr);
		printf("ss");
		mPath.push_back(temp);
		delete[] cstr;
	}
	arr.clear();

}


Path::~Path()
{
}

SinglePath* Path::getPath(int index){

	return &mPath.at(index);
}

SinglePath::SinglePath(char* line_map_data)
{
	std::vector<std::string> arr1 = split(line_map_data, ":");

	int n = arr1[1].find(',');
	start_x = std::stoi(trim(arr1[1].substr(0, n)));
	start_y = std::stoi(trim(arr1[1].substr(n + 1)));

	std::vector<std::string> sarr = split(arr1[2],",");
	n = sarr.size();
	this->size = n;

	int p;
	for (int i = 0; i < n; i++) {
		p = sarr[i].find('-');
		dir[i] = std::stoi(trim(sarr[i].substr(0, p)));
		len[i] = std::stoi(trim(sarr[i].substr(p + 1)));
	}

	sarr.clear();
}
