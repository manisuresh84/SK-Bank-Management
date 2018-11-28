/*angular.module('saveuser', []).controller(
		'SaveUser',
		function($scope, $http) {
			$http.post('http://localhost:8080/bankmanagement/api/users').then(
					function(response) {
						$scope.userdata = response.data;
					});
		});

 */

angular.module('saveuser', []).controller('SaveUser', function($scope, $http) {
	$scope.user = {};
	$scope.submitForm = function() {
		$http({
			method : 'POST',
			url : 'http://localhost:8080/bankmanagement/api/users',
			data : $scope.user,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(data) {
			if (data.errors) {
				$scope.errorAccNo = data.errors.accno;
				$scope.errorAccType = data.errors.acctype;
				$scope.errorAccName = data.errors.accname;
				$scope.errorAccBal = data.errors.accbal;
			} else {
				$scope.message = data.message;
			}
		});
	};
});