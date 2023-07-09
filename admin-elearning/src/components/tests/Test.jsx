import React , {useEffect , useState} from "react";
import { API_URL } from "../../consts/path";
import {API_CALL} from "../../api/api_call";
import { useSelector, useDispatch } from 'react-redux';
import { getAll } from '../../redux-slices/categorySlice' 
import categoryApi from "../../api/category/category_api";
import '../../styles/_main.scss';


function Test() {

    const [test, getTest] = useState();

    const categories = useSelector((state) => state.category.categories);
    const dispatch = useDispatch();

    const getCategory = async  () => {
        let formData = new FormData();

        let data = await categoryApi.getAll();

        dispatch(getAll(data))


        
        

//         let json = JSON.stringify({title: "Test nha"});
// let blob = new Blob([json], {
//   type: "application/json"
// });
        
//         formData.append("dto", blob, {contentType : "application/json"})




//

        // let response = await API_CALL.postFormData("http://localhost:8080/api/v1/admin/categories/save", formData);


    }

    useEffect(() => {
       getCategory();


    },[])

    console.log(categories)
    return (
        <React.Fragment>
            <div className="test-container">
                <div className="test-input">
                    <span>Label</span>
                    <input />
                </div>
                <div className="test-input">
                    <span>Label</span>
                    <input />
                </div>
                <div className="test-input">
                    <span>Label</span>
                    <input />
                </div>
                <div className="test-input">
                    <span>Label</span>
                    <input />
                </div>
                <div className="test-scss">Hello world</div>
            </div>
        </React.Fragment>
    )
}

export default Test;
