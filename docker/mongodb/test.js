db.skills.aggregate([
    {$match: {langs: {"$in": ["Java 7"]}}},
    {
        $project: {
            "result": {
                $reduce: {
                    input: {
                        $filter: {
                            input: ["Java 7"],
                            as: "skill",
                            cond: {
                                $and: [
                                    {$lte: [0, {$indexOfArray: ["$langs", "$$skill"]}]},
                                    {$gt: [20, {$indexOfArray: ["$langs", "$$skill"]}]}
                                ]
                            }
                        }
                    },
                    initialValue: 0,
                    in: {
                        $add: [
                            "$$value",
                            {
                                $subtract: [
                                    20,
                                    {$indexOfArray: ["$langs", "$$this"]}
                                ]
                            }
                        ]
                    }
                }
            },
            "personId": 1
        }
    },
    {$sort: {"result": -1}},
    {$skip: 0},
    {$limit: 5}
])