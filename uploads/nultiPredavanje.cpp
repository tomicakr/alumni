#include <iostream>
#include <assert.h>
#include <stdlib.h>
#include <list>

struct Point{
	int x; int y;
};
class Shape {
public :
	Point center_;
	virtual void draw ()=0;
	Shape(Point center){
		center_ = center;
	}
};
class Circle : public Shape {
public:
	Circle(Point center, int radius) : Shape(center){
		center_ = center;
		radius_ = radius;
	}
	int radius_;
	Point center_;
	virtual void draw(){
		std::cerr << "in drawCircle" << std::endl;
	}
};
class Square : public Shape {
public:
	int side_;
	Point center_;
	virtual void draw(){
		std::cerr << "in drawSquare" << std::endl;
	}
	Square(Point center, int side) : Shape(center){
		center_ = center;
		side_ = side;
	}
};
class Rhomb : public Shape {
public:
	int side_;
	int d1_;
	int d2_;
	Point center_;
	virtual void draw(){
		std::cerr << "in drawRhomb" << std::endl;
	}
	Rhomb(Point center, int side, int d1, int d2);
};
Rhomb::Rhomb(Point center, int side, int d1, int d2) : Shape(center){
	center_  = center;
	side_ = side;
	d1_ = d1;
	d2_ = d2;
}
void drawShapes(const std::list <Shape*>& fig){
	std::list<Shape*>::const_iterator it;
	for (it = fig.begin (); it != fig.end (); ++it){
		(*it)->draw();
	}
}

void moveShapes(const std::list <Shape*>& fig, int forX, int forY){
	std::list<Shape*>::const_iterator it;
	for (it = fig.begin (); it != fig.end (); ++it){
		(*it)->center_.x += forX;
		(*it)->center_.y += forY;
		(*it)->draw();
	}
}

int main(void){
	Point p1;
	p1.x = 20;
	p1.y = 30;
	Point p2;
	p2.x = 1;
	p2.x = 2;
	Point p3;
	p3.x = 230;
	p3.x = 402;
	Shape *r = new Rhomb(p1,3,10,2);
	Shape *c = new Circle(p2,5);
	Shape *s = new Square(p3,12);

	std::list<Shape*> l;
	l.push_back(r);
	l.push_back(c);
	l.push_back(s);
	drawShapes(l);
	std::cout << "Before moving: " << std::endl;
	std::cout << "r -> x:" << r->center_.x << " y: " << r->center_.y <<std::endl;
	moveShapes(l, 10, 4);
	std::cout << "After moving: " << std::endl;
	std::cout << "r -> x:" << r->center_.x << " y: " << r->center_.y <<std::endl;
	return 0;
}