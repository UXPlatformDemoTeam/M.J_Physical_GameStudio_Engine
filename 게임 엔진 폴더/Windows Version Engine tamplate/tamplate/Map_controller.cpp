#include "Map_controller.h"


Map_controller::Map_controller()
{
}


Map_controller::~Map_controller()
{
}

void	Map_controller::readMap(int map_Number)
{
	std::ifstream inFile(MAP_controllerNS::STARGFILE[map_Number], std::ifstream::binary);
	if (inFile.fail())               
	{
		throw(GameError(gameErrorNS::FATAL_ERROR, "Cannot Read File"));
		return;
	}

	inFile.seekg(0, inFile.end);
	int length = inFile.tellg();
	inFile.seekg(0, inFile.beg);

	char *buffer = new char[length];

	inFile.read(buffer, length);

	inFile.close();


	makeMap(buffer);


}


void	Map_controller::makeMap(char *data)
{
	std::string str(data);
	std::string temp;
	
	int n1 = str.find("selection:");

	temp = str.substr(0, n1);
	char *cstr = new char[temp.size() + 1];
	strcpy(cstr, temp.c_str());
	mPath = new Path(cstr);

	delete[] cstr;
	
	int n2 = str.find("delay:");
	n2 = n2 - n1;
	temp = str.substr(n1,n2);
	cstr = new char[temp.size() + 1];
	strcpy(cstr, temp.c_str());
	mSelect = new Selection(cstr);
	enemyCnt = mSelect->getEnemyCount();

	delete[] cstr;

	n1 = str.find("delay:");
	n2 = str.find("position") - str.find("delay:");;
	temp = str.substr(n1, n2);
	cstr = new char[temp.size() + 1];
	strcpy(cstr, temp.c_str());
	mDelay = new Delay(cstr);
	attackTime = mDelay->getDelay(0,5); 
	delete[] cstr;

	n1 = str.find("position:");
	n2 = str.find("shield") - str.find("position");;
	temp = str.substr(n1, n2);
	cstr = new char[temp.size() + 1];
	strcpy(cstr, temp.c_str());
	mPosition = new Position(cstr);
	delete[] cstr;

	n1 = str.find("shield:");
	temp = str.substr(n1);
	cstr = new char[temp.size() + 1];
	strcpy(cstr, temp.c_str());
	mEnemy_Life = new Enemy_life(cstr);
	delete[] cstr;


}

std::vector<std::string> split(std::string str, std::string sep){
	char* cstr = const_cast<char*>(str.c_str());
	char* current;
	std::vector<std::string> arr;
	current = strtok(cstr, sep.c_str());
	while (current != NULL){
		arr.push_back(current);
		current = strtok(NULL, sep.c_str());
	}
	return arr;
}

std::string trim(std::string& str)
{
	size_t first = str.find_first_not_of(' ');
	size_t last = str.find_last_not_of(' ');
	return str.substr(first, (last - first + 1));
}