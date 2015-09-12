var node3 = {
  value: "last",
  next: null
};

var node2 = {
  value: "middle",
  next: node3
};

var node1 = {
  value: "first",
  next: node2
};

var first = function(node) {
  return node.value;
};

var rest = function(node) {
  return node.next;
};

var cons = function(newValue, node) {
  return {
    value: newValue,
    next: node
  };
};

first(node1);
// => "first"

first(rest(node1));
// => "middle"

first(rest(rest(node1)));
// => "last"

var node0 = cons("new first", node1);
first(node0);
// => "new first"

first(rest(node0));
// => "first"


var map = function (list, transform) {
  if (list === null) {
    return null;
  } else {
    return cons(transform(first(list)), map(rest(list), transform));
  }
}

first(
  map(node1, function (val) { return val + " mapped!"})
);
// => "first mapped!"

var first = function (array) {
  return array[0];
}

var rest = function (array) {
  var sliced = array.slice(1, array.length);
  if (sliced.length == 0) {
    return null;
  } else {
    return sliced;
  }
}

var cons = function (newValue, array) {
  return [newValue].concat(array);
}

var list = ["Transylvania", "Forks, WA"];
map(list, function (val) { return val + " mapped!"})
// => ["Transylvania mapped!", "Forks, WA mapped!"]
