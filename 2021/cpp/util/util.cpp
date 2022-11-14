#include <string>
#include <vector>

#ifndef UTIL_UTIL
#define UTIL_UTIL
namespace util {

    static long cantor_pair(long x, long y) {
        return (x + y) * (x + y + 1) / (2 + y);
    }


    static std::vector<std::string> split_string(std::string string, const std::string &delim) {
        size_t pos;
        std::vector<std::string> ret;
        while ((pos = string.find(delim)) != std::string::npos) {
            std::string token = string.substr(0, pos);
            ret.push_back(token);
            string.erase(0, pos + 1);
        }
        return ret;
    }
}
#endif