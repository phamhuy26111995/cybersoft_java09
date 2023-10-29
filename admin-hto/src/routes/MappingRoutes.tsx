import CategoryPage from "@/pages/category/CategoryPage";
import GroupPage from "@/pages/group/GroupPage";
import PermissionPage from "@/pages/permission/PermissionPage";
import ProductPage from "@/pages/product/ProductPage";
import UserDetailPage from "@/pages/user/UserDetailPage";
import UserPage from "@/pages/user/UserPage";
import {
  UserOutlined,
  BookOutlined,
  GroupOutlined,
  SketchOutlined,
  ApartmentOutlined
} from "@ant-design/icons";
import { Link } from "react-router-dom";

export const MappingRoutes = new Map();

MappingRoutes.set("permission.screen.read", {
  component: <PermissionPage />,
  path: "/permission",
  isDisplayToLeftMenu : true,
  menu: {
    key: "permission.screen.read",
    label: "Phân quyền",
    icon: (
      <Link to={"/permission"}>
        <ApartmentOutlined />
      </Link>
    ),
  },
});

MappingRoutes.set("user.screen.read", {
    component: <UserPage />,
    path: "/user",
    isDisplayToLeftMenu : true,
    menu: {
      key: "user.screen.read",
      label: "Người dùng",
      icon: (
        <Link to={"/user"}>
          <UserOutlined />
        </Link>
      ),
    
    },
  });

  MappingRoutes.set("user.screen.create", {
    component: <UserDetailPage />,
    path: "/user/new",
    isDisplayToLeftMenu : false,
    menu: {
      
      key: "user.screen.create",
      label: "Người dùng",
      icon: (
        <Link to={"/user/new"}>
          <UserOutlined />
        </Link>
      ),
    
    },
  });

  MappingRoutes.set("user.screen.update", {
    component: <UserDetailPage />,
    path: "/user/:userId",
    isDisplayToLeftMenu : false,
    menu: {
      
      key: "user.screen.update",
      label: "Người dùng",
      icon: (
        <Link to={"/user/:userId"}>
          <UserOutlined />
        </Link>
      ),
    
    },
  });


  MappingRoutes.set("product.screen.read", {
    component: <ProductPage />,
    path: "/product",
    isDisplayToLeftMenu : true,
    menu: {
      key: "product.screen",
      label: "Sản phẩm",
      icon: (
        <Link to={"/product"}>
          <SketchOutlined />
        </Link>
      ),
    },
  });

  MappingRoutes.set("product.screen.create", {
    component: <ProductPage />,
    path: "/product",
    isDisplayToLeftMenu : false,
    menu: {
      key: "product.screen",
      label: "Sản phẩm",
      icon: (
        <Link to={"/product"}>
          <BookOutlined />
        </Link>
      ),
    },
  });

  MappingRoutes.set("product.screen.update", {
    component: <ProductPage />,
    path: "/product",
    isDisplayToLeftMenu : false,
    menu: {
      key: "product.screen",
      label: "Sản phẩm",
      icon: (
        <Link to={"/product"}>
          <BookOutlined />
        </Link>
      ),
    },
  });


  MappingRoutes.set("category.screen.read", {
    component: <CategoryPage />,
    path: "/category",
    isDisplayToLeftMenu : true,
    menu: {
      key: "category.screen.read",
      label: "Danh mục",
      icon: (
        <Link to={"/category"}>
          <BookOutlined />
        </Link>
      ),
    },
  });


  MappingRoutes.set("group.screen.read", {
    component: <GroupPage />,
    path: "/group",
    isDisplayToLeftMenu : true,
    menu: {
      key: "group.screen",
      label: "Nhóm",
      icon: (
        <Link to={"/group"}>
          <GroupOutlined />
        </Link>
      ),
    },
  });


  