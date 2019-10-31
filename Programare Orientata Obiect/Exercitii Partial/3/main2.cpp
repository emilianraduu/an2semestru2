#include "Movie.h"
#include "WatchList.h"
#include <iostream>

using namespace std;

int main() {
    Movie movie1("Troy",      "2:10");
    Movie movie2("Spotlight", "3:02");
    Movie movie3("Inception", "2:43");
    
    WatchList watchList = { &movie1, &movie2, &movie3 };
    
    // watchList.Watch("Troy",       40);//watch Troy for 40 minutes
    // watchList.Watch("Spotlight", 180);//watch Spotlight for 182 minutes
    // watchList.Watch("Troy",       90);//continue watching Troy for another 130 minutes

    printf("%s", watchList.movie1);
    // for (auto movie : watchList) {
    //     std::cout << movie->GetName() << "-";
    //     if (movie->IsFinished()) {
    //         std::cout << "Watched";
    //     }
    //     else {
    //         std::cout << "Continue watching from minute: " << movie->GetMinute();
    //     }
    //     std::cout << std::endl;
    // }
    return 0;
}