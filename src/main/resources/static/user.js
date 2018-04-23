var app = angular.module("UserManagement", []);

// Controller Part
app.controller("UserController", function($scope, $http) {
    $scope.users = [];
    $scope.userForm = {
        id: 1,
        name: "",
        password: "",
        role: "",
        enabled: ""
    };

    $scope.roles = {
            ROLE_USER: "{\"id\":1,\"name\":\"ROLE_USER\"}",
            ROLE_ADMIN: "{\"id\":2,\"name\":\"ROLE_ADMIN\"}"
    };

    // Now load the data from server
    _refreshUserData();

    // HTTP POST/PUT methods for add/edit user
    // Call: http://localhost:8080/user
    $scope.submitUser = function() {

        var method = "";
        var url = "";

        if ($scope.userForm.id == -1) {
            method = "POST";
            url = '/admins';
        } else {
            method = "PUT";
            url = '/admins';
        }

        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.userForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    $scope.createUser = function() {
        _clearFormData();
    };

    // HTTP DELETE- delete user by Id
    // Call: http://localhost:8080/user/{id}
    $scope.deleteUser = function(user) {
        $http({
            method: 'DELETE',
            url: '/admins/' + user.id
        }).then(_success, _error);
    };

    // In case of edit
    $scope.editUser = function(user) {
        $scope.userForm.id = user.id;
        $scope.userForm.name = user.name;
        $scope.userForm.password = user.password;
        $scope.userForm.role = user.role.name;
        $scope.userForm.enabled = user.enabled;
    };

    // Private Method
    // HTTP GET- get all users collection
    // Call: http://localhost:8080/users
    function _refreshUserData() {
        $http({
            method: 'GET',
            url: '/admins'
        }).then(
            function(res) { // success
                $scope.users = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        _refreshUserData();
        _clearFormData();
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

    // Clear the form
    function _clearFormData() {
        $scope.userForm.id = -1;
        $scope.userForm.name = "";
        $scope.userForm.password = "";
        $scope.userForm.role = "";
        $scope.userForm.enabled = "";
    };
});
