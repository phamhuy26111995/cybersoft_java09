import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { getAll ,exportExcel } from "../../redux-slices/categorySlice";
import { Link } from "react-router-dom";
import {
  showLoading,
  hideLoading,
  showError,
} from "../../redux-slices/globalSlice";
import categoryApi from "../../api/category/category_api";
import { useEffect } from "react";
import { Card, Table, Image, Button } from "antd";
import PageHeader from "../PageHeader";
import { EyeFilled } from "@ant-design/icons";
import { PAGE_URL } from "../../consts/path";
import CreateButton from "../buttons/CreateButton";
import { useNavigate } from "react-router-dom";

const { Column } = Table;

function Category() {
  const categories = useSelector((state) => state.category.categories);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    getCategories();

    return () => {
      dispatch(hideLoading());
    };
  }, []);

  const getCategories = async () => {
    dispatch(showLoading());
    try {
      let data = await categoryApi.getAll();
      dispatch(getAll(data));
      dispatch(hideLoading());
    } catch (err) {
      dispatch(showError("Không thể lấy được danh mục"));
      dispatch(hideLoading());
    }
  };

  return (
    <React.Fragment>
      <PageHeader
        title="Quản lý danh mục"
        extra={
          <CreateButton
            handleEvent={() =>
              navigate(PAGE_URL.CATEGORIES.DETAIL.replace(":id", "new"))
            }
          />
        }
      />
      <Card
        headStyle={{ backgroundColor: "gray", textAlign: "left" }}
        size="default"
        title="Kết quả"
      >
        <Table dataSource={categories}>
          <Column
            key={"action"}
            title="Hành động"
            render={(record) => (
              <Link to={PAGE_URL.CATEGORIES.INDEX + "/" + record.id}>
                <EyeFilled style={{ fontSize: "30px" }} />
              </Link>
            )}
          />
          <Column
            key={"numberOrder"}
            title="Số thứ tự"
            dataIndex="numberOrder"
          />
          <Column key={"title"} title="Tiêu đề" dataIndex="title" />
          <Column
            key={"icon"}
            title="Icon"
            dataIndex="icon"
            render={(record) => <Image src={record} style={{maxWidth : '100px'}}/>}
          />
        </Table>
      </Card>
      <Button onClick={() => dispatch(exportExcel())}>Export Excel`</Button>
    </React.Fragment>
  );
}

export default Category;
