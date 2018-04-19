var app = angular.module("UserManagement", []);

// Controller Part
app.controller("UserController", function($scope, $http) {
    $scope.users = [];
    $scope.userForm = {
        userId: 1,
        userName: "",
        userPassword: "",
        userRole: "",
        userEnabled: ""
    };

    $scope.roles = [
        {key:"2", name:"USER"},
        {key:"1", name:"ADMIN"}
    ];

    $scope.enableds = [
        {key:"0", name:"FALSE"},
        {key:"1", name:"TRUE"}
    ];

    // Now load the data from server
    _refreshUserData();

    // HTTP POST/PUT methods for add/edit user
    // Call: http://localhost:8080/user
    $scope.submitUser = function() {

        var method = "";
        var url = "";

        if ($scope.userForm.userId == -1) {
            method = "POST";
            url = '/users';
        } else {
            method = "PUT";
            url = '/users';
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
    // Call: http://localhost:8080/user/{userId}
    $scope.deleteUser = function(user) {
        $http({
            method: 'DELETE',
            url: '/users/' + user.id
        }).then(_success, _error);
    };

    // In case of edit
    $scope.editUser = function(user) {
        $scope.userForm.userId = user.id;
        $scope.userForm.userName = user.name;
        $scope.userForm.userPassword = user.password;
        $scope.userForm.userRole = user.role.name;
        $scope.userForm.userEnabled = user.enabled;
    };

    // Private Method
    // HTTP GET- get all users collection
    // Call: http://localhost:8080/users
    function _refreshUserData() {
        $http({
            method: 'GET',
            url: '/users'
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
        $scope.userForm.userId = -1;
        $scope.userForm.userName = "";
        $scope.userForm.userPassword = "";
        $scope.userForm.userRole = "";
        $scope.userForm.userEnabled = "";
    };
});
