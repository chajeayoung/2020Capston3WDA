export default function () {
  return [
    {
      title: "마이 페이지",
      htmlBefore: '<i class="material-icons">person</i>',
      to: "/user-profile-lite",

    },
    {
      title: "홈",
      htmlBefore: '<i class="material-icons">vertical_split</i>',
      to: "/main",
    },
    {
      title: "소개",
      htmlBefore: '<i class="material-icons">note_add</i>',
      to: "/add-new-post",
    },
    {
      title: "오디션",
      htmlBefore: '<i class="material-icons">view_module</i>',
      to: "/components-overview",
    },
    {
      title: "투표",
      htmlBefore: '<i class="material-icons">table_chart</i>',
      to: "/tables",
    },
    {
      title: "커뮤니티",
      to: "/blog-overview",
      htmlBefore: '<i class="material-icons">edit</i>',
      htmlAfter: ""
    },
    {
      title: "굿즈샵",
      htmlBefore: '<i class="material-icons">error</i>',
      to: "/errors",
    }
  ];
}
