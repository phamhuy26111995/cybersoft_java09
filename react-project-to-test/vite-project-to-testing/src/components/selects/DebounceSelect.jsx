import { Select, Spin } from "antd";
import debounce from "lodash/debounce";
import React, { useMemo, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

const { Option } = Select;


const DEFAULT_LIST = [
    {
      id: 1,
      first_name: "Cordi",
      last_name: "Hudspeth",
      email: "chudspeth0@yandex.ru",
      gender: "Female"
    },
    {
      id: 2,
      first_name: "Rockey",
      last_name: "Dupree",
      email: "rdupree1@cloudflare.com",
      gender: "Male"
    },
    {
      id: 3,
      first_name: "Derrick",
      last_name: "Spreull",
      email: "dspreull2@goo.gl",
      gender: "Male"
    },
    {
      id: 4,
      first_name: "Tome",
      last_name: "Tunna",
      email: "ttunna3@sina.com.cn",
      gender: "Polygender"
    },
    {
      id: 5,
      first_name: "Sergei",
      last_name: "Blackboro",
      email: "sblackboro4@businesswire.com",
      gender: "Male"
    },
    {
      id: 6,
      first_name: "Yulma",
      last_name: "Tredgold",
      email: "ytredgold5@cbsnews.com",
      gender: "Male"
    },
    {
      id: 7,
      first_name: "Jud",
      last_name: "De Cruz",
      email: "jdecruz6@timesonline.co.uk",
      gender: "Male"
    },
    {
      id: 8,
      first_name: "Abbye",
      last_name: "Sickling",
      email: "asickling7@acquirethisname.com",
      gender: "Female"
    },
    {
      id: 9,
      first_name: "Gage",
      last_name: "Burnett",
      email: "gburnett8@ovh.net",
      gender: "Male"
    },
    {
      id: 10,
      first_name: "Hobart",
      last_name: "De La Salle",
      email: "hdelasalle9@seesaa.net",
      gender: "Male"
    }
  ]

export default function DebounceSelect({
  fetchOptions,
  debounceTimeout = 500,
  ...props
}) {
  const [fetching, setFetching] = useState(false);
  const [options, setOptions] = useState([...DEFAULT_LIST]);
  const dispatch = useDispatch();
  const { users,defaultList, inputList } = useSelector((state) => state.userSlice);

  const debounceFetcher = useMemo(() => {
    const loadOptions = async (value) => {
        if(value === "") {
            setOptions(DEFAULT_LIST);
            setFetching(false)
            return;
        }
        
        let response = await fetch('/fake_data/users.json');
        let data = await response.json();
        // const startIndex = (paging.pageNumber - 1) * paging.itemsPerPage;
        // const endIndex = startIndex + paging.itemsPerPage;
        let searchResult = data.filter(el => el.first_name.includes(value));


        console.log(searchResult)
        setOptions(searchResult);

        
    };
    return debounce(loadOptions, debounceTimeout);
  }, [fetchOptions, debounceTimeout]);

  


  return (
    <Select
      filterOption={false}
      onSearch={debounceFetcher}
      onBlur={() => setOptions(DEFAULT_LIST)}
      notFoundContent={fetching ? <Spin size="small" /> : null}
      {...props}
    >
      {options.map((el) => (
        <Option key={el.id} value={el.first_name}>{el.first_name}</Option>
      ))}
    </Select>
  );
}
