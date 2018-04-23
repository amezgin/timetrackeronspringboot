var app = angular.module("TimeManagement", []);

// Controller Part
app.controller("TimeController", function ($scope, $http) {
    $scope.result = "";

    // HTTP get method for add status
    // Call: http://localhost:8080/timeWork
    $scope.actionUser = function (action) {
        $http({
            method: 'GET',
            url: '/timeWork/' + action,
            data: action
        }).then(_success)
    };

    function _success(res) {
        $scope.result = res.data;
    }
});
