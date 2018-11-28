angular.module('userview', []).controller(
		'ViewUser',
		function($scope, $http) {
			$http.get('http://localhost:8080/bankmanagement/api/users/').then(
					function(response) {
						$scope.userdata = response.data;
					});
		});
