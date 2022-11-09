#include <exception>
#include <string>

class UnsupportedDayException: public std::exception {
    std::string message;
public:
    explicit UnsupportedDayException(const std::string& message) {
        this->message = message;
    }
    [[nodiscard]] const char * what() const noexcept override {
        return message.c_str();
    }
};