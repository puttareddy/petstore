set -x

helm_release_name=$1

for i in `seq 1 3`;
do
        kubectl delete pods ${helm_release_name}-health-check-test --ignore-not-found=true
	test_output=`helm test ${helm_release_name} --timeout 60 | grep 'PASSED'`
        echo ${test_output}
	
	if [ ! -z "${test_output}" ]; then
		echo "helm test PASSED!"
                exit 0
		break;	
        else
                echo "helm test FAILED!"
                STATUS=1
	fi
done

if [ "$STATUS" -eq 1 ]; then
      exit 1
fi

